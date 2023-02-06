#!/bin/bash
ipadd=$(hostname -I | awk '{print $1}')
KEYCLOAK_BPM_CLIENT_SECRET="e4bdbd25-1467-4f7f-b993-bc4b1944c943"
KEYCLOAK_URL="http://$ipadd:8080"
KEYCLOAK_URL_REALM="forms-flow-ai"
echo "Do you wish to continue installation that include ANALYTICS? [y/n]" 
read choice
if [[ $choice == "y" ]]; then
    ANALYTICS=1
elif [[ $choice == "n" ]]; then
    ANALYTICS=0
fi
echo "Confirm that your IPv4 address is $ipadd [y/n]"
read choice
if [[ $choice == "y" ]]; then
    ipadd=$ipadd
    echo "$ipadd"
elif [[ $choice == "n" ]]; then
    read -p "Enter your IP Adress: " ipadd
    echo "$ipadd"
fi
#############################################################
######################### main function #####################
#############################################################

function main
{
  keycloak
  if [[ $ANALYTICS == 1 ]]; then
    forms-flow-analytics
  elif [[ $ANALYTICS == 0 ]]; then
    forms-flow-forms
  fi
  forms-flow-bpm
  installconfig
  forms-flow-api
  forms-flow-web
}

#############################################################
######################## creating config.js #################
#############################################################

function installconfig
{
   mkdir ../configuration	
   cd ../configuration/
   pwd
   if [[ -f config.js ]]; then
     rm config.js
   fi
   window["_env_"]="{"
   NODE_ENV="production"
   REACT_APP_API_SERVER_URL="http://$ipadd:3001"
   REACT_APP_API_PROJECT_URL="http://$ipadd:3001"
   REACT_APP_KEYCLOAK_CLIENT="forms-flow-web"
   REACT_APP_KEYCLOAK_URL_REALM="forms-flow-ai"
   REACT_APP_KEYCLOAK_URL="http://$ipadd:8080"
   REACT_APP_WEB_BASE_URL="http://$ipadd:5000"
   REACT_APP_CAMUNDA_API_URI="http://$ipadd:8000/camunda"
   REACT_APP_WEBSOCKET_ENCRYPT_KEY="giert989jkwrgb@DR55"
   REACT_APP_APPLICATION_NAME="formsflow.ai"
   REACT_APP_WEB_BASE_CUSTOM_URL=""
   REACT_APP_USER_ACCESS_PERMISSIONS="{accessAllowApplications:false,accessAllowSubmissions:false}"
	
   echo window["_env_"] = "{">>config.js
   echo NODE_ENV:%NODE_ENV%>>config.js
   echo REACT_APP_API_SERVER_URL:$REACT_APP_API_SERVER_URL>>config.js
   echo REACT_APP_API_PROJECT_URL:$REACT_APP_API_PROJECT_URL>>config.js
   echo REACT_APP_KEYCLOAK_CLIENT:$REACT_APP_KEYCLOAK_CLIENT>>config.js
   echo REACT_APP_KEYCLOAK_URL_REALM:$REACT_APP_KEYCLOAK_URL_REALM>>config.js
   echo REACT_APP_KEYCLOAK_URL:$REACT_APP_KEYCLOAK_URL>>config.js
   echo REACT_APP_WEB_BASE_URL:$REACT_APP_WEB_BASE_URL>>config.js
   echo REACT_APP_CAMUNDA_API_URI:$REACT_APP_CAMUNDA_API_URI>>config.js
   echo REACT_APP_WEBSOCKET_ENCRYPT_KEY:$REACT_APP_WEBSOCKET_ENCRYPT_KEY>>config.js
   echo REACT_APP_APPLICATION_NAME:$REACT_APP_APPLICATION_NAME>>config.js
   echo REACT_APP_WEB_BASE_CUSTOM_URL:$REACT_APP_WEB_BASE_CUSTOM_URL>>config.js
   echo REACT_APP_USER_ACCESS_PERMISSIONS:$REACT_APP_USER_ACCESS_PERMISSIONS>>config.js
   echo "}";>>config.js
}

#############################################################
###################### forms-flow-Analytics #################
#############################################################

function forms-flow-analytics
{
    REDASH_HOST=http://$ipadd:7000
    PYTHONUNBUFFERED=0
    REDASH_LOG_LEVEL=INFO
    REDASH_REDIS_URL=redis://redis:6379/0
    POSTGRES_USER=postgres
    POSTGRES_PASSWORD=changeme
    POSTGRES_DB=postgres
    REDASH_COOKIE_SECRET=redash-selfhosted
    REDASH_SECRET_KEY=redash-selfhosted
    REDASH_DATABASE_URL=postgresql://postgres:changeme@postgres/postgres
    REDASH_CORS_ACCESS_CONTROL_ALLOW_ORIGIN=*
    REDASH_REFERRER_POLICY=no-referrer-when-downgrade
    REDASH_CORS_ACCESS_CONTROL_ALLOW_HEADERS=Content-Type, Authorization
    echo REDASH_HOST=$REDASH_HOST>>.env
    echo PYTHONUNBUFFERED=$PYTHONUNBUFFERED>>.env
    echo REDASH_LOG_LEVEL=$REDASH_LOG_LEVEL>>.env
    echo REDASH_REDIS_URL=$REDASH_REDIS_URL>>.env
    echo POSTGRES_USER=$POSTGRES_USER>>.env
    echo POSTGRES_PASSWORD=$POSTGRES_PASSWORD>>.env
    echo POSTGRES_DB=$POSTGRES_DB>>.env
    echo REDASH_COOKIE_SECRET=$REDASH_COOKIE_SECRET>>.env
    echo REDASH_SECRET_KEY=$REDASH_SECRET_KEY>>.env
    echo REDASH_DATABASE_URL=$REDASH_DATABASE_URL>>.env
    echo REDASH_CORS_ACCESS_CONTROL_ALLOW_ORIGIN=$REDASH_CORS_ACCESS_CONTROL_ALLOW_ORIGIN>>.env
    echo REDASH_REFERRER_POLICY=$REDASH_REFERRER_POLICY>>.env
    echo REDASH_CORS_ACCESS_CONTROL_ALLOW_HEADERS=$REDASH_CORS_ACCESS_CONTROL_ALLOW_HEADERS>>.env

    docker-compose -f analytics-docker-compose.yml run --rm server create_db
    docker-compose -f analytics-docker-compose.yml up --build -d
}

#############################################################
######################## forms-flow-bpm #####################
#############################################################

function forms-flow-bpm
{
    FORMSFLOW_API_URL=http://$ipadd:5000
    WEBSOCKET_SECURITY_ORIGIN=http://$ipadd:3000
    SESSION_COOKIE_SECURE=false

    echo KEYCLOAK_URL=$KEYCLOAK_URL >> .env
    echo KEYCLOAK_BPM_CLIENT_SECRET=$KEYCLOAK_BPM_CLIENT_SECRET >>.env
    echo FORMSFLOW_API_URL=$FORMSFLOW_API_URL >>.env
    echo WEBSOCKET_SECURITY_ORIGIN=$WEBSOCKET_SECURITY_ORIGIN >> .env
    echo SESSION_COOKIE_SECURE=${SESSION_COOKIE_SECURE} >> .env
    docker-compose up --build -d forms-flow-bpm
}

#############################################################
######################## forms-flow-webapi ##################
#############################################################

function forms-flow-api
{
    BPM_API_URL=http://$ipadd:8000/camunda
    if [[ $ANALYTICS == 1 ]]; then (
        echo What is your Redash API key?
        read INSIGHT_API_KEY
        INSIGHT_API_URL=http://$ipadd:7000
    )
    fi
    echo BPM_API_URL=$BPM_API_URL >> .env
    if [[ $ANALYTICS == 1 ]]; then ( 
        echo INSIGHT_API_URL=$INSIGHT_API_URL >> .env
        echo INSIGHT_API_KEY=$INSIGHT_API_KEY >> .env
    )
    fi
    docker-compose up --build -d forms-flow-webapi
}

#############################################################
######################## forms-flow-forms ###################
#############################################################

function forms-flow-forms
{
    cd ../docker-compose
    FORMIO_DEFAULT_PROJECT_URL=http://$ipadd:3001

    echo FORMIO_DEFAULT_PROJECT_URL=$FORMIO_DEFAULT_PROJECT_URL>>.env

    docker-compose up --build -d forms-flow-forms

}
function forms-flow-web
{
cd ../docker-compose/
docker-compose up --build -d forms-flow-web
echo "********************** formsflow.ai is successfully installed ****************************"
}

#############################################################
########################### Keycloak ########################
#############################################################

function keycloak
{
    cd ../docker-compose/
    if [[ -f .env ]]; then
     rm .env
    fi
    function defaultinstallation
    {
        echo WE ARE SETING UP OUR DEFAULT KEYCLOCK FOR YOU
        printf "%s " "Press enter to continue"
        read that
        echo Please wait, keycloak is setting up!
        docker-compose up --build -d keycloak
    }
}
function orderwithanalytics
{
  echo installation will be completed in the following order:
  echo 1. keycloak
  echo 2. analytics
  echo 3. forms
  echo 4. camunda
  echo 5. webapi
  echo 6. web
  printf "%s " "Press enter to continue"
  read that
  main
}
function withoutanalytics
{
  echo installation will be completed in the following order:
  echo 1. keycloak
  echo 2. forms
  echo 3. camunda
  echo 4. webapi
  echo 5. web 
  printf "%s " "Press enter to continue"
  read that
  main
}
if [[ $ANALYTICS == 1 ]]; then
    orderwithanalytics
elif [[ $ANALYTICS == 0 ]]; then
    withoutanalytics
fi

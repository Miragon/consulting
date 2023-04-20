@echo off

set /p choice=Do you want analytics to include in the installation? [y/n]
if %choice%==y (
    set /a analytics=1
) else (
    set /a analytics=0
)

call:find-my-ip
call:main %analytics% %keycloak%

echo ********************** formsflow.ai is successfully installed ****************************
pause

EXIT /B %ERRORLEVEL%


:: ================&&&&&&===  Functions  ====&&&&&&&&&============================

:: #############################################################
:: ################### Main Function ###########################
:: #############################################################

:main
    call:set-common-properties
    call:keycloak ..\docker-compose %~2 
    if %~1==1 (
        call:forms-flow-analytics ..\docker-compose
    )
    call:forms-flow-forms ..\docker-compose
    call:forms-flow-bpm ..\docker-compose
    call:config ..\docker-compose\configuration
    call:forms-flow-web ..\docker-compose
    call:forms-flow-api ..\docker-compose %~1
    EXIT /B 0
	
:: #############################################################
:: ################### Finding IP Address ######################
:: #############################################################

:find-my-ip
    FOR /F "tokens=4 delims= " %%i in ('route print ^| find " 0.0.0.0"') do set ip-add=%%i
    set /p choice=Confirm that your IPv4 address is %ip-add%? [y/n]
    if %choice%==y (
           EXIT /B 0
     ) else (
       set /p ip-add="What is your IPv4 address?"
     )
    EXIT /B 0
  
:set-common-properties
    set WEBSOCKET_ENCRYPT_KEY=giert989jkwrgb@DR55
    set KEYCLOAK_BPM_CLIENT_SECRET=e4bdbd25-1467-4f7f-b993-bc4b1944c943
    EXIT /B 0

:: #############################################################
:: ########################### Keycloak ########################
:: #############################################################

:keycloak

        if exist %~1\.env (
        del %~1\.env
        )
	    docker-compose -f %~1\docker-compose.yml up --build -d keycloak
		timeout 5
		set KEYCLOAK_URL=http://%ip-add%:8080
	)
 	EXIT /B 0
   
:: #############################################################
:: ################### forms-flow-forms ########################
:: #############################################################

:forms-flow-forms

    set FORMIO_DEFAULT_PROJECT_URL=http://%ip-add%:3001
    echo FORMIO_DEFAULT_PROJECT_URL=%FORMIO_DEFAULT_PROJECT_URL%>>%~1\.env
    docker-compose -f %~1\docker-compose.yml up --build -d forms-flow-forms
    timeout 5
    EXIT /B 0
	
:: #########################################################################
:: #########################   config.js    ################################
:: #########################################################################

:config

   if exist %~1\config.js (
        del %~1\config.js
    )
   set window["_env_"] = {
   set NODE_ENV= "production",
   set REACT_APP_API_SERVER_URL="http://%ip-add%:3001",
   set REACT_APP_API_PROJECT_URL="http://%ip-add%:3001",
   set REACT_APP_KEYCLOAK_CLIENT="forms-flow-web",
   set REACT_APP_KEYCLOAK_URL_REALM="forms-flow-ai",
   set REACT_APP_KEYCLOAK_URL="http://%ip-add%:8080",
   set REACT_APP_WEB_BASE_URL="http://%ip-add%:5000",
   set REACT_APP_BPM_URL="http://%ip-add%:8000/camunda",
   set REACT_APP_WEBSOCKET_ENCRYPT_KEY="giert989jkwrgb@DR55",
   set REACT_APP_APPLICATION_NAME="formsflow.ai",
   set REACT_APP_WEB_BASE_CUSTOM_URL="",
   set REACT_APP_USER_ACCESS_PERMISSIONS={accessAllowApplications:false, accessAllowSubmissions:false}
   
   echo window["_env_"] = {>>%~1\config.js
   echo NODE_ENV:%NODE_ENV%>>%~1\config.js
   echo REACT_APP_API_SERVER_URL:%REACT_APP_API_SERVER_URL%>>%~1\config.js
   echo REACT_APP_API_PROJECT_URL:%REACT_APP_API_PROJECT_URL%>>%~1\config.js
   echo REACT_APP_KEYCLOAK_CLIENT:%REACT_APP_KEYCLOAK_CLIENT%>>%~1\config.js
   echo REACT_APP_KEYCLOAK_URL_REALM:%REACT_APP_KEYCLOAK_URL_REALM%>>%~1\config.js
   echo REACT_APP_KEYCLOAK_URL:%REACT_APP_KEYCLOAK_URL%>>%~1\config.js
   echo REACT_APP_WEB_BASE_URL:%REACT_APP_WEB_BASE_URL%>>%~1\config.js
   echo REACT_APP_BPM_URL:%REACT_APP_BPM_URL%>>%~1\config.js
   echo REACT_APP_WEBSOCKET_ENCRYPT_KEY:%REACT_APP_WEBSOCKET_ENCRYPT_KEY%>>%~1\config.js
   echo REACT_APP_APPLICATION_NAME:%REACT_APP_APPLICATION_NAME%>>%~1\config.js
   echo REACT_APP_WEB_BASE_CUSTOM_URL:%REACT_APP_WEB_BASE_CUSTOM_URL%>>%~1\config.js
   echo REACT_APP_USER_ACCESS_PERMISSIONS:%REACT_APP_USER_ACCESS_PERMISSIONS%>>%~1\config.js
   echo };>>%~1\config.js
   EXIT /B 0
   

:: #########################################################################
:: ######################### forms-flow-web ################################
:: #########################################################################

:forms-flow-web

    docker-compose -f %~1\docker-compose.yml up --build -d forms-flow-web
    EXIT /B 0

:: #############################################################
:: ################### forms-flow-bpm ########################
:: #############################################################

:forms-flow-bpm

    SETLOCAL
    set FORMSFLOW_API_URL=http://%ip-add%:5000
    set WEBSOCKET_SECURITY_ORIGIN=http://%ip-add%:3000
    set SESSION_COOKIE_SECURE=false

    echo KEYCLOAK_URL=%KEYCLOAK_URL%>>%~1\.env
    echo KEYCLOAK_BPM_CLIENT_SECRET=%KEYCLOAK_BPM_CLIENT_SECRET%>>%~1\.env
    echo FORMSFLOW_API_URL=%FORMSFLOW_API_URL%>>%~1\.env
    echo WEBSOCKET_SECURITY_ORIGIN=%WEBSOCKET_SECURITY_ORIGIN%>>%~1\.env
    echo SESSION_COOKIE_SECURE=%SESSION_COOKIE_SECURE%>>%~1\.env
    ENDLOCAL
    docker-compose -f %~1\docker-compose.yml up --build -d forms-flow-bpm
    timeout 6
    EXIT /B 0  

:: #############################################################
:: ################### forms-flow-analytics ########################
:: #############################################################

:forms-flow-analytics

    SETLOCAL
    set REDASH_HOST=http://%ip-add%:7000
    set PYTHONUNBUFFERED=0
    set REDASH_LOG_LEVEL=INFO
    set REDASH_REDIS_URL=redis://redis:6379/0
    set POSTGRES_USER=postgres
    set POSTGRES_PASSWORD=changeme
    set POSTGRES_DB=postgres
    set REDASH_COOKIE_SECRET=redash-selfhosted
    set REDASH_SECRET_KEY=redash-selfhosted
    set REDASH_DATABASE_URL=postgresql://postgres:changeme@postgres/postgres
    set REDASH_CORS_ACCESS_CONTROL_ALLOW_ORIGIN=*
    set REDASH_REFERRER_POLICY=no-referrer-when-downgrade
    set REDASH_CORS_ACCESS_CONTROL_ALLOW_HEADERS=Content-Type, Authorization
    echo REDASH_HOST=%REDASH_HOST%>>%~1\.env
    echo PYTHONUNBUFFERED=%PYTHONUNBUFFERED%>>%~1\.env
    echo REDASH_LOG_LEVEL=%REDASH_LOG_LEVEL%>>%~1\.env
    echo REDASH_REDIS_URL=%REDASH_REDIS_URL%>>%~1\.env
    echo POSTGRES_USER=%POSTGRES_USER%>>%~1\.env
    echo POSTGRES_PASSWORD=%POSTGRES_PASSWORD%>>%~1\.env
    echo POSTGRES_DB=%POSTGRES_DB%>>%~1\.env
    echo REDASH_COOKIE_SECRET=%REDASH_COOKIE_SECRET%>>%~1\.env
    echo REDASH_SECRET_KEY=%REDASH_SECRET_KEY%>>%~1\.env
    echo REDASH_DATABASE_URL=%REDASH_DATABASE_URL%>>%~1\.env
    echo REDASH_CORS_ACCESS_CONTROL_ALLOW_ORIGIN=%REDASH_CORS_ACCESS_CONTROL_ALLOW_ORIGIN%>>%~1\.env
    echo REDASH_REFERRER_POLICY=%REDASH_REFERRER_POLICY%>>%~1\.env
    echo REDASH_CORS_ACCESS_CONTROL_ALLOW_HEADERS=%REDASH_CORS_ACCESS_CONTROL_ALLOW_HEADERS%>>%~1\.env
    ENDLOCAL
    docker-compose -f %~1\analytics-docker-compose.yml run --rm server create_db
    docker-compose -f %~1\analytics-docker-compose.yml up --build -d
	timeout 5
    EXIT /B 0

:: #############################################################
:: ################### forms-flow-api ########################
:: #############################################################

:forms-flow-api

    SETLOCAL

    set BPM_API_URL=http://%ip-add%:8000/camunda
    if %~2==1 (
        set /p INSIGHT_API_KEY="What is your Redash API key?"
        set INSIGHT_API_URL=http://%ip-add%:7000
    )
    echo BPM_API_URL=%BPM_API_URL%>>%~1\.env
    if %~2==1 (
        echo INSIGHT_API_URL=%INSIGHT_API_URL%>>%~1\.env
        echo INSIGHT_API_KEY=%INSIGHT_API_KEY%>>%~1\.env
    )
    
    ENDLOCAL
    docker-compose -f %~1\docker-compose.yml up --build -d forms-flow-webapi



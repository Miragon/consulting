#!/bin/bash

# Directory path is passed as an argument
PROJECT_DIR=$1

# Check if the directory path is provided
if [ -z "$PROJECT_DIR" ]; then
    echo "Error: Directory path not provided."
    exit 1
fi

# Change to the specified directory
cd "$PROJECT_DIR" || exit

# Find and delete files not ending in .kt
find . -type f ! -name "*.kt" -exec rm -f {} +

# Delete specific directories and their subdirectories
rm -rf .openapi-generator docs gradle

# Loop through each file ending with Api.kt
find . -type f -name "*Api.kt" | while IFS= read -r file; do
    # Check if we are on macOS or Linux and adjust the sed command accordingly
    if [[ "$OSTYPE" == "darwin"* ]]; then
        # macOS requires an empty string argument with -i
        sed -i '' 's/java.math.BigDecimal? = 1.toDouble()/java.math.BigDecimal? = 1.toBigDecimal()/g' "$file"
    else
        # Linux does not require an empty string argument with -i
        sed -i 's/java.math.BigDecimal? = 1.toDouble()/java.math.BigDecimal? = 1.toBigDecimal()/g' "$file"
    fi
done

echo "Non-Kotlin files and specified directories removed in $PROJECT_DIR."
echo "Replacements made in Api.kt files."
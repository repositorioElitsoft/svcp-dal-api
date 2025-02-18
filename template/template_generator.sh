#!/bin/bash

# Script to generate CRUD-related files from templates.

# Check if app_name.info exists
if [[ -f "app_name.info" ]]; then
  app_name=$(cat "app_name.info")
  echo "Application name read from app_name.info: $app_name"
else
  read -p "Enter the application name (e.g., shoppingcart): " app_name
fi

# Convert app name to lowercase.
app_name_lower=$(echo "$app_name" | tr '[:upper:]' '[:lower:]')

# Ask for the entity name (e.g., "Client").
read -p "Enter the entity name (e.g., Client): " entity_name

# Convert entity name to lowercase.
entity_name_lower=$(echo "$entity_name" | tr '[:upper:]' '[:lower:]')

# Construct the source (template) directory path.
template_dir="./"

# Construct the destination directory paths.
dest_java_dir="../src/main/java/com/elitsoft/${app_name_lower}"
dest_resource_dir="../src/main/resources/mapper"

# Create the destination directories if they don't exist.
mkdir -p "$dest_java_dir/controller/core"
mkdir -p "$dest_java_dir/controller/mobile"
mkdir -p "$dest_java_dir/domain/dto/core"
mkdir -p "$dest_java_dir/domain/dto/mobile"
mkdir -p "$dest_java_dir/domain/entity"
mkdir -p "$dest_java_dir/exceptions"
mkdir -p "$dest_java_dir/mapper"
mkdir -p "$dest_java_dir/mapstruct"
mkdir -p "$dest_java_dir/security"
mkdir -p "$dest_java_dir/service/core"
mkdir -p "$dest_java_dir/service/mobile"
mkdir -p "$dest_java_dir/utils"
mkdir -p "$dest_resource_dir"

files=(
    "controller/core/BaseController.java $dest_java_dir/controller/core/${entity_name}Controller.java 1"
    "controller/mobile/BaseMobileController.java $dest_java_dir/controller/mobile/${entity_name}MobileController.java 1"
    "domain/dto/core/BaseDto.java $dest_java_dir/domain/dto/core/${entity_name}Dto.java 1"
    "domain/dto/mobile/BaseMobileDto.java $dest_java_dir/domain/dto/mobile/${entity_name}MobileDto.java 1"
    "domain/entity/Base.java $dest_java_dir/domain/entity/${entity_name}.java 1"
    "exceptions/BaseNoEncontradoException.java $dest_java_dir/exceptions/${entity_name}NoEncontradoException.java 1"
    "mapper/BaseMapper.java $dest_java_dir/mapper/${entity_name}Mapper.java 1"
    "mapstruct/BaseMapStruct.java $dest_java_dir/mapstruct/${entity_name}MapStruct.java 1"
    "service/core/BaseService.java $dest_java_dir/service/core/${entity_name}Service.java 1"
    "service/mobile/BaseMobileService.java $dest_java_dir/service/mobile/${entity_name}MobileService.java 1"
    "resources/mapper/BaseMapper.xml $dest_resource_dir/${entity_name}Mapper.xml 1"
)

file_count=0

printf "%-60s %-60s\n" "Source File" "Destination File"

for file in "${files[@]}"; do
    IFS=' ' read -r source_file dest_file rename_file <<< "$file"

    # Check if the destination file exists
    if [[ -f "$dest_file" ]]; then
        read -p "Destination file '$dest_file' already exists. Override? (yes/no): " override
        case "$override" in
            yes|y|Yes|Y)
                # Proceed with copy if override is yes
                ;;
            no|n|No|N)
                echo "Skipping '$dest_file'."
                continue  # Skip to the next file
                ;;
            *)
                echo "Invalid input. Skipping '$dest_file'."
                continue # Skip to the next file
                ;;
        esac
    fi

    if [[ "$rename_file" -eq 1 ]]; then
        cp "$template_dir/$source_file" "$dest_file"
        printf "%-60s %-60s\n" "$template_dir/$source_file" "$dest_file"
    else
        cp "$template_dir/$source_file" "$dest_file"
        printf "%-60s %-60s\n" "$template_dir/$source_file" "$dest_file"
    fi

    file_count=$((file_count + 1))

    temp_file=$(mktemp)

    sed "s/#Base#/$entity_name/g" "$dest_file" > "$temp_file"
    sed "s/#base#/$entity_name_lower/g" "$temp_file" > "$temp_file.1"
    sed "s/#app_name#/$app_name_lower/g" "$temp_file.1" > "$temp_file.2"
    sed "s/com.elitsoft.servicampo/com.elitsoft.$app_name_lower/g" "$temp_file.2" > "$temp_file.3"

    # Portable uppercase conversion
    entity_name_upper=$(echo "$entity_name" | tr '[:lower:]' '[:upper:]')
    sed "s/#BASE#/$entity_name_upper/g" "$temp_file.3" > "$dest_file"

    rm "$temp_file" "$temp_file.1" "$temp_file.2" "$temp_file.3"

    if [[ "$source_file" == "resources/mapper/BaseMapper.xml" ]]; then
        temp_file=$(mktemp)
        sed "s/com.elitsoft.#app_name#.mapper.BaseMapper/com.elitsoft.$app_name_lower.mapper.${entity_name}Mapper/g" "$dest_file" > "$temp_file"
        sed "s/#base#/${entity_name_lower}/g" "$temp_file" > "$dest_file"
        rm "$temp_file"
    fi

done

# Append/Update Constantes.java
constantes_file="../src/main/java/com/elitsoft/${app_name_lower}/utils/Constantes.java"
template_constantes_file="./utils/Constantes.java"

if [[ -f "$constantes_file" ]]; then
    # Read existing constantes file content into an array of lines
    existing_constantes_lines=()
    while IFS= read -r line; do
        existing_constantes_lines+=("$line")
    done < "$constantes_file"

    # Find the line number of the closing bracket
    bracket_line_number=0
    for i in "${!existing_constantes_lines[@]}"; do
        if [[ "${existing_constantes_lines[$i]}" == "}" ]]; then
            bracket_line_number=$i
            break
        fi
    done

    # If the closing bracket is not found, add it at the end
    if [[ "$bracket_line_number" -eq 0 ]]; then
        bracket_line_number=${#existing_constantes_lines[@]}
        existing_constantes_lines+=("}")
    fi

    # Extract constants from template file and insert them
    new_constants=()
    while IFS= read -r line; do
        if [[ "$line" =~ "public static final String #Base#_" ]]; then
            constant_name=$(echo "$line" | awk -F'String ' '{print $2}' | awk -F' = ' '{print $1}')
            constant_value=$(echo "$line" | awk -F'"' '{print $2}')

            # Replace placeholders (UPPERCASE)
            constant_name=$(echo "$constant_name" | sed "s/#Base#/$entity_name_upper/g")
            constant_value=$(echo "$constant_value" | sed "s/#Base#/$entity_name_upper/g")

            new_constants+=("    public static final String $constant_name = \"$constant_value\";")
        fi
    done < "$template_constantes_file"

    # Insert new constants before the closing bracket
    if [[ ${#new_constants[@]} -gt 0 ]]; then
        existing_constantes_lines=("${existing_constantes_lines[@]:0:$bracket_line_number}" "${new_constants[@]}" "${existing_constantes_lines[@]:$bracket_line_number}")
    fi

    # Clear the file
    > "$constantes_file"

    # Re-write all lines
    for line in "${existing_constantes_lines[@]}"; do
        echo "$line" >> "$constantes_file"
    done
fi


echo "Files generated successfully in $dest_java_dir and $dest_resource_dir"
echo "Total files processed: $file_count"

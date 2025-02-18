# template_generator.ps1

# Application Name Handling
if (Test-Path -Path "app_name.info") {
    $app_name = Get-Content -Path "app_name.info"
    Write-Host "Application name read from app_name.info: $app_name"
} else {
    $app_name = Read-Host "Enter the application name (e.g., shoppingcart)"
}

# Convert app name to lowercase
$app_name_lower = $app_name.ToLower()

# Entity Name Input
$entity_name = Read-Host "Enter the entity name (e.g., Client)"

# Convert entity name to lowercase
$entity_name_lower = $entity_name.ToLower()

# Construct Destination Directories
$template_dir = "./"
$dest_java_dir = "../src/main/java/com/elitsoft/$app_name_lower"
$dest_resource_dir = "../src/main/resources/mapper"

# Create Destination Directories
New-Item -ItemType Directory -Path "$dest_java_dir/controller/core" -Force
New-Item -ItemType Directory -Path "$dest_java_dir/controller/mobile" -Force
New-Item -ItemType Directory -Path "$dest_java_dir/domain/dto/core" -Force
New-Item -ItemType Directory -Path "$dest_java_dir/domain/dto/mobile" -Force
New-Item -ItemType Directory -Path "$dest_java_dir/domain/entity" -Force
New-Item -ItemType Directory -Path "$dest_java_dir/exceptions" -Force
New-Item -ItemType Directory -Path "$dest_java_dir/mapper" -Force
New-Item -ItemType Directory -Path "$dest_java_dir/mapstruct" -Force
New-Item -ItemType Directory -Path "$dest_java_dir/security" -Force
New-Item -ItemType Directory -Path "$dest_java_dir/service/core" -Force
New-Item -ItemType Directory -Path "$dest_java_dir/service/mobile" -Force
New-Item -ItemType Directory -Path "$dest_java_dir/utils" -Force
New-Item -ItemType Directory -Path "$dest_resource_dir" -Force

$files = @(
    "controller/core/BaseController.java <span class="math-inline">dest\_java\_dir/controller/core/</span>{entity_name}Controller.java 1",
    "controller/mobile/BaseMobileController.java <span class="math-inline">dest\_java\_dir/controller/mobile/</span>{entity_name}MobileController.java 1",
    "domain/dto/core/BaseDto.java <span class="math-inline">dest\_java\_dir/domain/dto/core/</span>{entity_name}Dto.java 1",
    "domain/dto/mobile/BaseMobileDto.java <span class="math-inline">dest\_java\_dir/domain/dto/mobile/</span>{entity_name}MobileDto.java 1",
    "domain/entity/Base.java <span class="math-inline">dest\_java\_dir/domain/entity/</span>{entity_name}.java 1",
    "exceptions/BaseNoEncontradoException.java <span class="math-inline">dest\_java\_dir/exceptions/</span>{entity_name}NoEncontradoException.java 1",
    "mapper/BaseMapper.java <span class="math-inline">dest\_java\_dir/mapper/</span>{entity_name}Mapper.java 1",
    "mapstruct/BaseMapStruct.java <span class="math-inline">dest\_java\_dir/mapstruct/</span>{entity_name}MapStruct.java 1",
    "service/core/BaseService.java <span class="math-inline">dest\_java\_dir/service/core/</span>{entity_name}Service.java 1",
    "service/mobile/BaseMobileService.java <span class="math-inline">dest\_java\_dir/service/mobile/</span>{entity_name}MobileService.java 1",
    "resources/mapper/BaseMapper.xml <span class="math-inline">dest\_resource\_dir/</span>{entity_name}Mapper.xml 1"
)

$file_count = 0

Write-Host ("{0,-60} {1,-60}" -f "Source File", "Destination File")

foreach ($file in $files) {
    $parts = $file.Split(" ")
    $source_file = "<span class="math-inline">template\_dir</span>($parts[0])"
    $dest_file = $parts[1]
    $rename_file = [int]$parts[2]

    # Check if the destination file exists
    if (Test-Path -Path $dest_file) {
        $override = Read-Host "Destination file '$dest_file' already exists. Override? (yes/no)"
        if (<span class="math-inline">override \-match "^\(yes\|y\)</span>") {
            # Proceed with copy if override is yes
        } elseif (<span class="math-inline">override \-match "^\(no\|n\)</span>") {
            Write-Host "Skipping '$dest_file'."
            continue # Skip to the next file
        } else {
            Write-Host "Invalid input. Skipping '$dest_file'."
            continue # Skip to the next file
        }
    }

    if ($rename_file -eq 1) {
        Copy-Item -Path $source_file -Destination $dest_file
        Write-Host ("{0,-60} {1,-60}" -f $source_file, $dest_file)
    } else {
        Copy-Item -Path $source_file -Destination $dest_file
        Write-Host ("{0,-60} {1,-60}" -f $source_file, $dest_file)
    }

    $file_count++

    $temp_file = New-TemporaryFile
    $temp_file1 = New-TemporaryFile
    $temp_file2 = New-TemporaryFile
    $temp_file3 = New-TemporaryFile

    (Get-Content -Path $dest_file) -replace "#Base#", $entity_name | Set-Content -Path $temp_file
    (Get-Content -Path $temp_file) -replace "#base#", $entity_name_lower | Set-Content -Path $temp_file1
    (Get-Content -Path $temp_file1) -replace "#app_name#", $app_name_lower | Set-Content -Path $temp_file2
    (Get-Content -Path $temp_file2) -replace "com.elitsoft.servicampo", "com.elitsoft.$app_name_lower" | Set-Content -Path $temp_file3

    $entity_name_upper = $entity_name.ToUpper()
    (Get-Content -Path $temp_file3) -replace "#BASE#", $entity_name_upper | Set-Content -Path $dest_file

    Remove-Item -Path $temp_file, $temp_file1, $temp_file2, $temp_file3

    if ($parts[0] -eq "resources/mapper/BaseMapper.xml") {
        $temp_file = New-TemporaryFile
        (Get-Content -Path $dest_file) -replace "com.elitsoft.#app_name#.mapper.BaseMapper", "com.elitsoft.<span class="math-inline">app\_name\_lower\.mapper\.</span>{entity_name}Mapper" | Set-Content -Path $temp_file
        (Get-Content -Path $temp_file) -replace "#base#", $entity_name_lower | Set-Content -Path $dest_file
        Remove-Item -Path $temp_file
    }
}

# Append/Update Constantes.java
$constantes_file = "$dest_java_dir/utils/Constantes.java"
$template_constantes_file = "./utils/Constantes.java"

if (Test-Path -Path $constantes_file) {
    $existing_constantes_lines = Get-Content -Path $constantes_file

    $bracket_line_number = 0
    for ($i = 0; $i -lt $existing_constantes_lines.Length; $i++) {
        if ($existing_constantes_lines[$i] -eq "}") {
            $bracket_line_number = $i
            break
        }
    }

    if ($bracket_line_number -eq 0) {
        $bracket_line_number = $existing_constantes_lines.Length
        $existing_constantes_lines += "}"
    }

    $new_constants = @()
    $template_lines = Get-Content -Path $template_constantes_file
    foreach ($line in $template_lines) {
        if ($line -match "public static final String #Base#_") {
            $constant_name = ($line -split "String ")[1] -split " = "[0]
            $constant_value = ($line -split '"')[1]

            $entity_name_upper = $entity_name.ToUpper()
            $constant_name = $constant_name -replace "#Base#", $entity_name_upper
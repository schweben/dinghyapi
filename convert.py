import sys

excelFilename = sys.argv[1]
changeSetId = sys.argv[2]

rowCounter = 0

yamlFilename = excelFilename.replace('csv', 'yaml')
dinghies = {}

with open(excelFilename, "r") as excelCsv:
    for line in excelCsv:
        if rowCounter == 0:
            rowCounter += 1 # Skip the column header
        else:
            # Save the CSV as a dictionary
            name, manufacturer, yardstick, crew, rig, hulls, symmetric_spinnaker, asymmetric_spinnaker, trapeze, length, beam, hull_weight, sail_area, spinnaker_area, logo_path, image_path = line.strip().split(',')

            dinghies[name] = {'name': name,
                              'manufacturer': manufacturer,
                              'yardstick': yardstick,
                              'crew': crew,
                              'rig': rig,
                              'hulls': hulls,
                              'symmetric_spinnaker': symmetric_spinnaker.lower(),
                              'asymmetric_spinnaker':asymmetric_spinnaker.lower(),
                              'trapeze':trapeze,
                              'length': length,
                              'beam': beam,
                              'hull_weight': hull_weight,
                              'sail_area': sail_area,
                              'spinnaker_area': spinnaker_area,
                              'logo_path': logo_path,
                              'image_path': image_path}

with open(yamlFilename, "w+") as yf:
    yf.write("databaseChangeLog:\n")
    yf.write("  - changeSet:\n")
    yf.write(f"      id: {changeSetId}\n")
    yf.write("      author: Iain Soars\n")
    yf.write("      changes:\n")

    for dinghy in dinghies:
        yf.write("        - insert:\n")
        yf.write("            tableName: dinghies\n")
        yf.write("            columns:\n")

        for k,v in dinghies[dinghy].items():
            if v != '':
                yf.write("              - column:\n")
                yf.write(f"                 name: {k}\n")
                yf.write(f"                 value: '{v}'\n")

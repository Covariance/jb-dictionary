import sys
from os import makedirs
from os.path import isfile, dirname, sep, exists, basename, splitext

if __name__ == "__main__":
    dict_path = sys.argv[1]
    if not isfile(dict_path):
        print("Provided path is not a file")
        exit()
    folder = dirname(dict_path) + sep + 'generated_' + \
             splitext(basename(dict_path))[0]

    if not exists(folder):
        makedirs(folder)

    dictionary = open(dict_path, 'r')

    dict = {}
    for line in dictionary.readlines():
        if line[0] not in dict:
            dict[line[0]] = []

        dict[line[0]].append(line)

    dictionary.close()

    for key in dict.keys():
        key_file = open(folder + sep + key, 'w')
        key_file.writelines(sorted(dict[key]))
        key_file.close()

import sys

file_name = sys.argv[1]

#ucitavanje datoteke
#genome = open("Escherichia_coli_bl21_gold_de3_plyss_ag_.GCA_000023665.1.24.dna.toplevel.fa", "r")
genome = open(file_name, "r")
lines = genome.readlines()
limits = (100, 500, 1000, 5000, 10000, 50000, 100000, 250000, 500000, 1000000)

file_counter = 0
for limit in limits:
    out_string = ""
    file_counter += 1
    for line in lines:
        if line[0] == ">":
            continue
        else:
            string = line.strip()

        if len(out_string) > limit:
            out_file_name = "input_%i" % file_counter
            out_file = open(out_file_name, "w")
            out_file.write(out_string)
            out_file.close()
            break
        else:
            out_string += string

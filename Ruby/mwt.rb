arity = ARGV[0]
#filepath = ARGV[1]
filepath = "../generate_inputs/inputs/input_3"

# assuming that the input file is a FASTA file

sequence = ""

f = File.open(filepath, 'r')
f.each_line do |line|
	if line[0] == '>'
		continue
	else
		sequence+=line
	end
end
f.close

print "\n"
print sequence
print "\n"

#arity = ARGV[0]
#filepath = ARGV[1]
arity = "4"
filepath = "../generate_inputs/inputs/input_3"

arity = arity.to_i

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

alphabet = sequence.split(//).uniq

alphabet_ascii = []

for character in alphabet do
	alphabet_ascii << character.ord
	end

alphabet_basen = []

for character in alphabet_ascii do
	alphabet_basen << character.to_s(arity)
	end

print "\n"
print alphabet_basen

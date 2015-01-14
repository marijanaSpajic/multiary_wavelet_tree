include Math

#arity = ARGV[0]
#filepath = ARGV[1]
arity = "4"
filepath = "../generate_inputs/inputs/input_3"

arity = arity.to_i
base = log(arity,2)
base = 7/base
base = base.ceil
base = base.to_i

# reading input file, assuming that the input is a FASTA file

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


# get alphabet

alphabet = sequence.split(//).uniq

alphabet_ascii = []

for character in alphabet do
	alphabet_ascii << character.ord
	end

alphabet_basen = []

for character in alphabet_ascii do
	alphabet_basen << character.to_s(base)
	end


# translation dictionary from original alphabet to base_n

transformation = { alphabet[0] => alphabet_basen[0], alphabet[1] => alphabet_basen[1], alphabet[2] => alphabet_basen[2], alphabet[3] => alphabet_basen[3] }


# translating the input sequence

sequence_transformed = []

i = 0

while i<sequence.length do
	sequence_transformed << transformation[sequence[i]]
	i+=1
	end


# building layers
# base = number of layers
# layer array = [ [layer lvl_n], [layers lvl_n-1], layers lvl_n-2, ... , layers lvl_1 ]




layers = []

i = 0
while i<base do
	layers << []
	i+=1
	end

i=0
while i<base-1 do
	layers[i+1] << layers[i]
	layers[i+1] << layers[i]
	layers[i+1] << layers[i]
	layers[i+1] << layers[i]
	i+=1
	end






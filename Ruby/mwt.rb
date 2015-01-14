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



# test data

#sequence_transformed = ["312", "003", "030", "111", "233", "003", "101", "312", "112", "303"]
#sequence_transformed = ["1003", "1013", "1110", "1001", "1013"]

#sequence_transformed = sequence_transformed[0:4]





# building layers
# base = number of layers
# layer array = [ [layer lvl_n], [layers lvl_n-1], layers lvl_n-2, ... , layers lvl_1 ]

#layers = []
#
#i = 0
#while i<=base do
#	layers << []
#	i+=1
#	end
#
#i=0
#while i<base do
#	j=0
#	while j<=base do
#		layers[i+1] << layers[i]
#		j+=1
#		end
#	i+=1
#	end


# this bit is pretty nasty and one should strive to do better
# problem: ruby uses pointers instead of making copies of arrays in the loop above





if base == 3
	layers = [[],[[],[],[],[]],[[[],[],[],[]],[[],[],[],[]],[[],[],[],[]],[[],[],[],[]]]]
	end
if base == 4
	layers = [[], [[], [], [], []], [[[], [], [], []], [[], [], [], []], [[], [], [], []], [[], [], [], []]], [[[[], [], [], []], [[], [], [], []], [[], [], [], []], [[], [], [], []]], [[[], [], [], []], [[], [], [], []], [[], [], [], []], [[], [], [], []]], [[[], [], [], []], [[], [], [], []], [[], [], [], []], [[], [], [], []]], [[[], [], [], []], [[], [], [], []], [[], [], [], []], [[], [], [], []]]]]
	end

# this bit only really works up to base 4

for item in sequence_transformed do
	layers[0] << item[0]
	if base >=2
		layers[1][item[0].to_i] << item[1]
		end
	if base >=3
		layers[2][item[0].to_i][item[1].to_i] << item[2]
		end
	if base >=4
		layers[3][item[0].to_i][item[1].to_i][item[2].to_i] << item[3]
		end
	end


for layer in layers
	print "\n"
	print layer
	print "\n"
	end





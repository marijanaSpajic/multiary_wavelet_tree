include Math

# multiary wavelet tree implementation
# currently, the script works only for trees up to and including base 4

arity = ARGV[0]
filepath = ARGV[1]
#arity = "4"
#filepath = "../generate_inputs/inputs/input_3"

arity = arity.to_i
base = log(arity,2)
base = 7/base
base = base.ceil
base = base.to_i


def findRank(position, symbol, i, layers)
	count = 0

	if i==0
		j=0
		while j<= position do
			if layers[0][j] == symbol[i]
				count+=1
				end
			j+=1
			end
		end

	if i==1
		j=0
		while j<= position do
			if layers[1][symbol[0].to_i][j] == symbol[i]
				count+=1
				end
			j+=1
			end
		end


	if i==2
		j=0
		while j<= position do
			if layers[2][symbol[0].to_i][symbol[1].to_i][j] == symbol[i]
				count+=1
				end
			j+=1
			end
		end

	if i==3
		j=0
		while j<= position do
			if layers[3][symbol[0].to_i][symbol[1].to_i][symbol[2].to_i][j] == symbol[i]
				count+=1
				end
			j+=1
			end
		end	

	position = count

	i+=1

	if i<symbol.length

		position = findRank(position, symbol, i, layers)
	else
		return position
		end

end



def doSelect(rank, symbol, i, layers)

	if i == 3
		j=0
		count =0
		while count<=rank do
			if layers[3][symbol[0].to_i][symbol[1].to_i][symbol[2].to_i][j] == symbol[i]
				count+=1
				index = j
				end
			j+=1
			end
		end

	if i == 2
		j=0
		count =0
		while count<=rank do
			if layers[2][symbol[0].to_i][symbol[1].to_i][j] == symbol[i]
				count+=1
				index = j
				end
			j+=1
			end
		end

	if i == 1
		j=0
		count =0
		while count<=rank do
			if layers[1][symbol[0].to_i][j] == symbol[i]
				count+=1
				index = j
				end
			j+=1
			end
		end

	if i == 0
		j=0
		count =0
		while count<=rank do
			if layers[0][j] == symbol[i]
				count+=1
				index = j
				end
			j+=1
			end
		end

	i-=1

	if i==-1
		return index
	else
		index = doSelect(index+1, symbol, i, layers)
		end

end


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
#		layers[i+1] << layers[i].clone
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

inp = [""]

while inp[0] != "exit" do
	print "\nTree built.\nRank or Select?\n\n[ rank <position> <symbol> ]   [ select <rank> <symbol> ]  [ exit ]\n\n"

	inp = gets

	inp = inp.split

	if inp[0] == "rank"
		position = inp[1].to_i
		symbol = inp[2]
		symbol_transformed = transformation[symbol]

		# do rank
		# 4-level tree

		res = findRank(position, symbol_transformed, 0, layers)

		print res
		print "\n"

		end

	if inp[0] == "select"
		rank = inp[1].to_i
		symbol = inp[2]
		symbol_transformed = transformation[symbol]
	
		# do select

		res = doSelect(rank, symbol_transformed, 3, layers)

		print res
		print "\n"

		end
	
	end


print "The End\n\n"


include Math

# multiary wavelet tree implementation
# currently, the script works only for trees up to and including arity 5 (base 4)

start_time = Time.now

filepath = ARGV[0]
arity = ARGV[1]
function = ARGV[2]
arg1 = ARGV[3]
arg2 = ARGV[4]

arity = arity.to_i
base = log(arity,2)
base = 7/base
base = base.ceil
base = base.to_i


# implementation of rank function
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


#implementation of select function
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


# create translation dictionary from original alphabet to base_n

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


# the following section is pretty nasty and one should strive to do better
# problem: ruby uses pointers instead of making copies of arrays in the loop above
# therefore the tree and mwt functions are hardcoded for up to arity 5 (base 4)

if base == 3
	layers = [[],[[],[],[],[]],[[[],[],[],[]],[[],[],[],[]],[[],[],[],[]],[[],[],[],[]]]]
	end
if base == 4
	layers = [[], [[], [], [], []], [[[], [], [], []], [[], [], [], []], [[], [], [], []], [[], [], [], []]], [[[[], [], [], []], [[], [], [], []], [[], [], [], []], [[], [], [], []]], [[[], [], [], []], [[], [], [], []], [[], [], [], []], [[], [], [], []]], [[[], [], [], []], [[], [], [], []], [[], [], [], []], [[], [], [], []]], [[[], [], [], []], [[], [], [], []], [[], [], [], []], [[], [], [], []]]]]
	end


# arrange data in tree

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

inp[0] = function
inp[1] = arg1
inp[2] = arg2

if inp[0] == "rank"

	rank_start_time = Time.now

	position = inp[1].to_i
	symbol = inp[2]
	symbol_transformed = transformation[symbol]

		# do rank

	res = findRank(position, symbol_transformed, 0, layers)

	rank_end_time = Time.now

	print "\n"
	print inp[0]
	print " "
	print inp[1]
	print " "
	print inp[2]
	print " : "
	print res
	print "\n"
	print "\n"

	end

if inp[0] == "select"

	select_start_time = Time.now

	rank = inp[1].to_i
	symbol = inp[2]
	symbol_transformed = transformation[symbol]
	
		# do select

	res = doSelect(rank, symbol_transformed, 3, layers)

	select_end_time = Time.now

	print "\n"
	print inp[0]
	print " "
	print inp[1]
	print " "
	print inp[2]
	print " : "
	print res
	print "\n"
	print "\n"

	end
	
built_time = Time.now


print "Total time: \n"
print built_time-start_time
print "\n"
print "\n"


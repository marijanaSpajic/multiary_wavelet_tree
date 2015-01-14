#!/usr/bin/perl
use strict;
use warnings;
use POSIX;

#log in base 2
sub log2 {
	my $n = shift;
	return log($n)/log(2);
}

#calculating number of layers with given formula
sub get_num_layers{
	my $n = shift;
	return ceil(7/log2($n));
}


#converting number from base 10 to wanted base by taking modulus as next digit
sub convert_to_base{
	my $num = shift;
	my $base = shift;
	my $digit = 0;
	my @converted = ();
	
	while ($num > 0){
		$digit = $num % $base;
		unshift(@converted, $digit);
		$num = floor($num / $base);
	}
	
	#adding zeros if number has too few digits
	my $size_converted = @converted; 
	while ($size_converted < $base){
		unshift (@converted, 0);
		$size_converted = $size_converted + 1;
	}	
	return @converted;
}

#calculating rank of the symbol
sub rank{
	my $position = shift;
	my $symbol = shift;
	my $layers = shift;
	my $mwt = shift;	
	
	my $count = 0;
	my $j = 0;
	
	my @characters = convert_to_base(ord($symbol), $layers);
	my %tree = %$mwt;
	
	#checking if the digits are in the wright place in the tree, first root, then a rest of tree
	for (my $j = 0; $j < $position; $j++){
		if ($characters[0] == $tree{'root'}[$j]){
			$count++;
		}
	}
	$position = $count;
	$count = 0;
	
	my $key = $characters[0];
	
	for (my $i=1; $i < $layers; $i++){
		for (my $j = 0; $j < $position; $j++){
			if (exists ($tree{$key}[$j])){
				
			if ($characters[$i] == $tree{$key}[$j]){
				$count++;
			}}
		}
		$key=$key . $characters[$i];
		$position = $count;
		$count = 0;
	}	
	print "$position";	
}

#function select, returns position of the symbol with the given rank
sub select_mwt {
	my $rank = shift;
	my $symbol = shift;
	my $layers = shift;
	my $mwt = shift;
	
	my $count = 0;
	my @characters = convert_to_base(ord($symbol), $layers);
	my %tree = %$mwt;

	my $key ="";
	
	foreach my $character (@characters) {
		$key = $key . $character;
	}
	$key = substr($key, 0, -1);
	
	my $j=0;
	my $position = 0;
	
	#counting characters that match
	for (my $i=$layers-1; $i >= 0; $i--){
		
		while ($rank > $count){			
			if (exists ($tree{$key}[$j])){
			if ($characters[$i] == $tree{$key}[$j]){
				$count++;
			}}
			$j++;
		}
		$rank = $j;
		$position = $j;
		$j=0;
		$count = 0;
		
		print "$key ";
		$key = substr($key, 0, -1);
		if ($key eq ""){
			$key='root';
		}
		
	}
	
	print "$position";
	
	
}

my $name = $ARGV[0];                      					#file specified in argument
open (my $file, '<', $name) or die $!;    					#open file with given filename

my $multiplicity = $ARGV[1];
my $num_layers  = get_num_layers($multiplicity);

my $function = $ARGV[2];							#rank or select
my $parameter1 = $ARGV[3];							#for rank, this is position, and for select, this is rank
my $parameter2 = $ARGV[4];							#A, C, G or T


#reading the characters from the given file (spliting the file to the array of characters)
my @characters = [];
while(<$file>) {
						#$_ = lc($_); # convert everything to lowercase  
    @characters = split (//, $_);  
}

my @root = ();
my %mwt=();

#converting characters and putting them into the mw tree
foreach my $char (@characters){
	
	my $char_ord = ord($char);
	my @converted_chars = convert_to_base($char_ord, $num_layers);
	
	unless (exists $mwt{'root'}){
		$mwt{'root'} = [];
	}
	push ($mwt{'root'}, $converted_chars[0]);
	
	my $string = "" . $converted_chars[0];
	
	#using hash table as a tree
	for (my $i=1; $i < $num_layers; $i++) {
		unless (exists $mwt{$string}){
			$mwt{$string} = [];
		}
		push ($mwt{$string}, $converted_chars[$i]);
		
		$string = $string . $converted_chars[$i];
	} 
}


if ($function eq 'rank'){
	rank ($parameter1, $parameter2, $num_layers, \%mwt);
}
if ($function eq 'select'){
	select_mwt ($parameter1, $parameter2, $num_layers, \%mwt);
}


close($file);                             #close the input file



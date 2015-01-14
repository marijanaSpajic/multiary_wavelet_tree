#!/usr/bin/perl
use strict;
use warnings;
use POSIX;


sub log2 {
	my $n = shift;
	return log($n)/log(2);
}

sub get_num_layers{
	my $n = shift;
	return ceil(7/log2($n));
}


#converting number from base 10 to wanted base by taking modulus division and division
sub convert_to_base{
	my $num = shift;
	my $base = shift;
	my $place = 0;
	my $digit = 0;
	my $converted = 0;
	
	while ($num > 0){
		$digit = $num % $base;
		$converted = $converted + $digit*(10 ** $place);    #numbers must go from back to front, ** is exp
		$place = $place + 1;
		$num = floor($num / $base);
	}
	
	return $converted;
}

my $name = $ARGV[0];                      #file specified in argument
open (my $file, '<', $name) or die $!;    #open file with given filename



#my $proba = get_num_layers(5);           testing - get_num_layers
#my $proba = convert_to_base(67, 4);      testing - convert_to_base
#print $proba;

#print <$file>;                           testing - reading the file

close($file);                             #close the input file
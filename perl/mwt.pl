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



my $name = $ARGV[0];                      #file specified in argument
open (my $file, '<', $name) or die $!;    #open file with given filename

#my $proba = get_num_layers(5);           testing - get_num_layers
#print $proba;

#print <$file>;                           testing - reading the file
close($file);                             #close the input file
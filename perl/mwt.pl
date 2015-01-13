#!/usr/bin/perl
use strict;
use warnings;

my $name = $ARGV[0];                      #file specified in argument
open (my $file, '<', $name) or die $!;    #open file with given filename


print <$file>;
close($file);                             #close the input file
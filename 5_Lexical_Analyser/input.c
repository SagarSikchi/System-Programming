#include<stdio.h>

int maximum_number ( int p, int q ) {
	if ( p < q ) {
		return q;
	}
	else {
		return p;
	}
}

int add ( int w, int z) {
	return w + z;
}

int main() {
	int a, b, c, x = 10, y = 20, z = 30;
	float d = 100.0, e;
	char ch1 = 's', ch2 = '#';
	a = b = 5;
	c = 6;
	if ( a > b || x & y ) {
		c += a - b;
		x *= c;
		e = d - 10.0;
		e = add( a, b );
	}
	else {
		d -= e + 6.0;
		b = a + c;
		int M = maximum_number( x, y );
	}
	return 0;
}


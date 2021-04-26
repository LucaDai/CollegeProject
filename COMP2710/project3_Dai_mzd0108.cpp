/*
Luca Dai
mzd0108
project3_Dai_mzd0108.cpp
compile project3_Dai_mzd0108.cpp then run it in Linux AU service.
Taking the input from two file and sorting them from small to big number
Finally put them in the output file.
Using Dr.Li's hint template.
*/

#include <fstream>
#include <iostream>
#include <vector>

using namespace std;

const int MAX_SIZE = 100;

/* A method to check the file exist. */
bool check_file(string);

/* To read variable from exist files. */
vector<int> read_file(string);

/*To write sorted variables(array) into a new file. */
void write_file(string, vector<int>);

/*Combine two array in to one, and sort it.*/
vector<int> merge(vector<int>, vector<int>);

/*
* Display the values of a given vector.
*
* Param: file Name of file to display. (string)
* Param: v Vector containing values to display. (vector<int>)
*/
void to_string(string, vector<int>);

/*
* Merge the numbers in two specified files and write all the numbers
* to a specified third file sorted in ascending order.
*
* Return: 1 Program completed successfully. (int)
*/

int main() {
	/* input and output */
	string file1;
	string file2;
	string file3;
	/*Vector of variable in different files. */
	vector<int> numbers1;
	vector<int> numbers2;
	vector<int> numbers3;

	cout << "*** Welcome to Dai's sorting program ***\n";

	/* Get name of file one. */
	do {
		cout << "Enter the first input file name: ";
		cin >> file1;

	} while (cin.fail() || !check_file(file1));

	/* Get and display numbers from file one. */
	numbers1 = read_file(file1);
	cout << "The list of " << numbers1.size()
		<< " numbers in file " << file1 << " is:\n";
	to_string(file1, numbers1);
			
	/* Get name of file two. */
	do {
		cout << "Enter the second input file name: ";
		cin >> file2;

	} while (cin.fail() || !check_file(file2));

	/* Get and display numbers from file two. */
	numbers2 = read_file(file2);
	cout << "The list of " << numbers2.size() 
		<< " numbers in file "<< file2 <<" is:\n";
	to_string(file2, numbers2);

	/* Combine vectors and display the sorted result. */
	numbers3 = merge(numbers1, numbers2);
	cout << "The sorted list of " << numbers3.size() << " numbers is: ";
	for (int i = 0; i < numbers3.size(); i++) {
		cout << numbers3.at(i) << " ";
	}
	cout << "\n";

	/* Get name of output file. */
	do {
		cout << "Enter the output file name: ";
		cin >> file3;

	} while (cin.fail());

	/* Write combined vector to output file. */
	write_file(file3, numbers3);
	cout << "*** Please check the new file - " << file3 << " ***\n";
	cout << "*** Goodbye. ***";

	return 1;
}


bool check_file(string file) {
	/* Input file stream. (ifstream) */
	ifstream stream;
	/* Check whether file exists. */
	stream.open(file.c_str());
	if (stream.fail()) {
		cerr << "File not found.\n\n";
		return false;

	}
	stream.close();
	return true;
}

vector<int> read_file(string file) {
	/* Input file stream. (ifstream) */
	ifstream stream;
	/* Vector containing numbers from file. (vector<int>) */
	vector<int> v;
	/* Integer read from file. (int) */
	int i;

	/* Add each number in the file to a vector. */
	stream.open(file.c_str());

	while (stream.good()) {
		stream >> i;
		v.push_back(i); // Add element at the end

	}
	v.pop_back(); // Delete last element
	return v;
	stream.close();
}

void write_file(string file, vector<int> v) {
	/* Output file stream. (ofstream) */
	ofstream stream;
	unsigned short i;
	stream.open(file.c_str());
	for (i = 0; i < (v.size() - 1); i++) {
		stream << v.at(i) << "\n";
	}
	stream << v.at(v.size() - 1);
	stream.close();
}

vector<int> merge(vector<int> v1, vector<int> v2) {
	/* output vector v3. */
	vector<int> v3;
	int i = 0;
	int j = 0;
	/* Compare both vectors. */
	while ((i != v1.size()) && (j != v2.size())) {
		if (v1.at(i) < v2.at(j)) {
			v3.push_back(v1.at(i));
			i++;
		}
		else {
			v3.push_back(v2.at(j));
			j++;
		}
	}
	/* Add any remaining numbers from vector one. */
	while (i != v1.size()) {
		v3.push_back(v1.at(i));
		i++;
	}
	/* Add any remaining numbers from vector two. */
	while (j != v2.size()) {
		v3.push_back(v2.at(j));
		j++;
	}
	return v3;
}
void to_string(string file, vector<int> v) {
	/* Vector interator number. (unsigned short) */
	unsigned short i;
	/* Display the numbers contained in a vector. */
	for (i = 0; i < v.size(); i++) {
		cout << v.at(i) << "\n";
	}
	cout << "\n";
}


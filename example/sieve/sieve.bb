decl numbers:addr16
decl curr_base_number:addr16
decl prev_base_number:addr16
decl curr_val:addr16
decl prev_val:addr16
decl curr_multiple:addr16
decl prev_multiple:addr16

let numbers := 0x0200
let curr_base_number := 0x0201
let prev_base_number := 0x0202
let curr_val := 0x0203
let prev_val := 0x0204
let curr_multiple := 0x0205
let prev_multiple := 0x0206

func init():nothing
begin
    let curr_val[1,0] := 0
    let prev_val[1,0] := 0;

    ; this odd type of while loop is necessary because
    ; no value above 255 exists.  so we can't do the
    ; equivalent of for(i=0; i<256; i++) because it isn
    ; not possible to represent the number 256 in 8 bits.
    ; what we can do is check for rollover. so 255 + 1 = 0
    ; so if the current value is 0 and the previous value
    ; is 255 then we know we are done
    while (prev_val[1,0] <= curr_val[1,0])
    begin
	let numbers[1,curr_val[1,0]] := 0
	let prev_val[1,0] := curr_val[1,0]
	let curr_val[1,0] := curr_val[1,0] + 1
    end
end

func find_primes():nothing
begin
    ; 0 is not prime
    ; 1 is prime
    let numbers[1,0] := 0
    let numbers[1,1] := 1

    ; start the sieve at 2
    let curr_base_number[1,0] := 2
    let prev_base_number[1,0] := 2
    
    while (prev_base_number[1,0] <= curr_base_number[1,0])
    begin
	let prev_multiple[1,0] := curr_base_number[1,0]
	let curr_multiple[1,0] := curr_base_number[1,0]

	while (prev_multiple[1,0] <= curr_multiple[1,0])
	begin
	    let numbers[1, curr_multiple[1,0]] := curr_multiple[1,0]
	    let prev_multiple[1,0] := curr_multiple[1,0]
	    let curr_multiple[1,0] := curr_multiple[1,0] + curr_base_number[1,0]
	end

	let prev_base_number[1,0] := curr_base_number[1,0]
	let curr_base_number[1,0] := curr_base_number[1,0] + 1
    end

    let curr_base_number[1,0] := 0
    let prev_base_number[1,0] := 0

    while (prev_base_number[1,0] <= curr_base_number[1,0])
    begin
	if (numbers[1,curr_base_number[1,0]] != 0)
	begin
	    ;print numbers[1,curr_base_number[1,0]] is prime
	end

	let prev_base_number[1,0] := curr_base_number[1,0]
	let curr_base_number[1,0] := curr_base_number[1,0] + 1
    end
end


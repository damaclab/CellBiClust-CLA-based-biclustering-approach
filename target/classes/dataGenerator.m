# MATLAB code to generate binary matrix of dimenation m * n with k 1's
function a=genMat(m,n,p)
	a = zeros(m,n);
	k = int32(m*n*p);
	a(randperm(numel(a), k)) = 1
endfunction
x=genMat(10,10,0.1)
path="example.csv"
csvwrite(path,x)
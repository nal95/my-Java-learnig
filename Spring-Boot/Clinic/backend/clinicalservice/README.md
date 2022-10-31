to generate private and public key  with rsa : 
$ openssl genrsa -out key.pem 2048
$ openssl rsa -in key.pem -outform PEM -pubout -out public.pem


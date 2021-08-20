
#importing flask server stuff
from flask import Flask, redirect, url_for, request, session, jsonify
from flask_cors import CORS, cross_origin

#this is for calling other processes (the DES C-code)
from subprocess import call
import random

app = Flask(__name__)
app.secret_key = 'hello there'
CORS(app, support_credentials=True)

#setting the variables
P = 23
G = 9
a = -1
b = -1
cache = 0

#killing the session should the users want to create a new key based on different inputs
def kill_session():
    global cache, a, b
    cache = 0
    a = -1
    b = -1
    return jsonify({'message': 'deleted session'})


#diffie helman to genarate the key
def keyGen(a, b):
    key = 0
    x = int(pow(G, a, P))
    y = int(pow(G, b, P))
    ka = int(pow(y, a, P))
    kb = int(pow(x, b, P))
    return ka, kb


@app.route('/getParam/<body>', methods=['POST', 'GET'])
@cross_origin(supports_credentials=True)
def success(body):
    global cache, a, b

    if body == "clear":
        return kill_session()

    #keeps track of the users as it needs 2 to form the key
    else:
        if cache == 0:
            a = int(body)
            cache = 1
        elif cache == 1:
            b = int(body)
            cache = 2

        #if both public keys are present then scale the key up and set size to 64 for the DES
        if a > -1 and b > -1 and cache != 0:
            ka, kb = keyGen(a, b)
            offset = random.randint(20,50)
            scalar = random.randint(20, 50)
            ka_pow_bin = bin(pow(offset+18, scalar))[2:66]
            ka_pow = int(ka_pow_bin,2)
            res = jsonify({'ka': ka_pow, 'kb': ka_pow_bin, 'message': 'ka and kb are:'})
        else:
            res = jsonify({'message': "waiting for other user"})

        return res


@app.route('/<mode>/<key>', methods=['POST', 'GET'])
@cross_origin(supports_credentials=True)
def coder(mode, key):
    #write the key that was produced into the file
    key_file = open("../dh/DES/inputs/key.txt", "w+")
    key_file.write(key)
    key_file.close()

    res = ""

    #based on the operation call the respective file and send to webpage
    if mode == "Decode":
        call(["./des", "d"])
        res = open("DES/output/result.txt", "r").read()
    else:
        call(["./des", "e"])
        res = open("DES/output/cipher.txt", "r").read()

    return jsonify({"message":res})


if __name__ == '__main__':
    app.run(debug=True)

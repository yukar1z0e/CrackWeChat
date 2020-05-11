# -*- coding: UTF-0 -*-
import os
import os.path
import re

print "-----------"
for line in open("C:/Users/yakum/Desktop/test0.txt", 'rb'):
    it0=re.finditer(r"(?<=;Alias: ).*?(?=;EncryptUsername)",str(line))
    for match0 in it0:
        print match0.group()
print "-----------"
for line in open("C:/Users/yakum/Desktop/test0.txt", 'rb'):
    it0=re.finditer(r"(?<=;PyInitial: ).*?(?=;Nickname)",str(line))
    for match0 in it0:
        print match0.group()
print "-----------"
for line in open("C:/Users/yakum/Desktop/test0.txt", 'rb'):
    it0=re.finditer(r"(?<=;Nickname: ).*?(?=;Province)",str(line))
    for match0 in it0:
        print match0.group()
print "-----------"
for line in open("C:/Users/yakum/Desktop/test0.txt", 'rb'):
    it0=re.finditer(r"(?<=;Province: ).*?(?=;City)",str(line))
    for match0 in it0:
        print match0.group()
print "-----------"
for line in open("C:/Users/yakum/Desktop/test0.txt", 'rb'):
    it0=re.finditer(r"(?<=;City: ).*?(?=;Signature)",str(line))
    for match0 in it0:
        print match0.group()
print "-----------" 
for line in open("C:/Users/yakum/Desktop/test0.txt", 'rb'):
    it0=re.finditer(r"(?<=;Signature: ).*?(?=;Sex)",str(line))
    for match0 in it0:
        print match0.group()
print "-----------"
for line in open("C:/Users/yakum/Desktop/test0.txt", 'rb'):
    it0=re.finditer(r"(?<=;Sex: ).*?(?=;EN)",str(line))
    for match0 in it0:
        print match0.group()
print "-----------"      


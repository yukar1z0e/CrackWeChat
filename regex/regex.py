# -*- coding: UTF-8 -*-
import os
import os.path
import re

print "-----------"
for line in open("C:/Users/yakum/Desktop/test4.txt", 'rb'):
    it1=re.finditer(r"(?<=;Alias: ).*?(?=;EncryptUsername)",str(line))
    for match1 in it1:
        print match1.group()
print "-----------"
for line in open("C:/Users/yakum/Desktop/test4.txt", 'rb'):
    it2=re.finditer(r"(?<=;PyInitial: ).*?(?=;Nickname)",str(line))
    for match2 in it2:
        print match2.group()
print "-----------"
for line in open("C:/Users/yakum/Desktop/test4.txt", 'rb'):
    it3=re.finditer(r"(?<=;Nickname: ).*?(?=;Province)",str(line))
    for match3 in it3:
        print match3.group()
print "-----------"
for line in open("C:/Users/yakum/Desktop/test4.txt", 'rb'):
    it4=re.finditer(r"(?<=;Province: ).*?(?=;City)",str(line))
    for match4 in it4:
        print match4.group()
print "-----------"
for line in open("C:/Users/yakum/Desktop/test4.txt", 'rb'):
    it5=re.finditer(r"(?<=;City: ).*?(?=;Signature)",str(line))
    for match5 in it5:
        print match5.group()
print "-----------" 
for line in open("C:/Users/yakum/Desktop/test4.txt", 'rb'):
    it6=re.finditer(r"(?<=;Signature: ).*?(?=;Sex)",str(line))
    for match6 in it6:
        print match6.group()
print "-----------"
for line in open("C:/Users/yakum/Desktop/test4.txt", 'rb'):
    it7=re.finditer(r"(?<=;Sex: ).*?(?=;EN)",str(line))
    for match7 in it7:
        print match7.group()
print "-----------"      


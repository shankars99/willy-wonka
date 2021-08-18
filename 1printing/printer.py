import warnings
warnings.filterwarnings('ignore')

import json
from subprocess import run, DEVNULL


f = open('links.json',)

data = json.load(f)

for topic_name in data:
	i = 1
	for topic_url in data[topic_name]:
		file_name = topic_name + "-" + str(i)
		print(file_name +"-"+ topic_url + "   saving")
		print_job = ['wkhtmltopdf',topic_url,file_name]
		run(print_job, stdout=DEVNULL, stderr=DEVNULL)
		i+=1

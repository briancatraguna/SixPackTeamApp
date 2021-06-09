# How to use the compute engine to create your own API for the flask server

## Cloud Console
1. Go to [Google Cloud Platform Console](https://console.cloud.google.com/)
2. Go to Compute Engine and choose VM instances.
3. Create your own instance by clicking on `+ create instance` button.
4. Configure the settings, default is OK.
5. Go to the instances tab, you will see your new instance appear.
6. Make an SSH connection by hovering to the most - right column and click on `ssh`

## Linux Terminal
Now that you are connected with the server, perform the following commands below to install the correct dependencies:
1. `sudo apt-get update`
2. `sudo apt-get install libgl1-mesa-glx libegl1-mesa libxrandr2 libxrandr2 libxss1 libxcursor1 libxcomposite1 libasound2 libxi6 libxtst6`
3. `sudo apt-get install wget`
4. `wget https://repo.anaconda.com/archive/Anaconda3-2021.05-Linux-x86_64.sh`
5. `bash Anaconda3-2021.05-Linux-x86_64.sh`
6. `rm Anaconda3-2021.05-Linux-x86_64.sh`
7. `source .bashrc`
8. To verify installation, run these commands
* `python --version`
*  `which python`
*  `which conda`
*  `conda list conda$`
9. Now create a new directory, you can name it anything, e.g `mkdir my_project` and then go to the directory by using `cd my_project`
10. Now its time to run Jupyterlab by running these commands
* `jupyter lab --ip 0.0.0.0 --port 8888 --no-browser`
*  After running that, you will be able to receive a token to open Jupyterlab on your browser. Now you can go to browser and use this URL:
`<External IP>:8888/lab?token=<token>`
<br>You can find the External IP on your Google Cloud Console in the VM Instances of the Compute Engine</br>

## Jupyterlab
1. After successfully opening the Jupyterlab on your browser, move either `classification.py` or `ner.py` to the Jupyterlab directory.
2. Now rename the Python script as `app.py`
3. Move the `requirements.txt` also to the Jupyterlab directory.
4. Open the Jupyterlab terminal by clicking on `File -> New Terminal`
5. Run this command: `pip install requirements.txt`
6. Then you're set! Now run the server by doing this command `flask run -h 0.0.0.0`

## You need to repeat again to create another virtual machine to run the other model. Repeat from the top!

## Extras
- To stop the flask server click `CTRL + C`
- To use the API run with this URL on the browser:
`<External IP>:5000/?input=<YOUR_INPUT_TO_THE_MODEL_HERE>`
- To give your more context, these are example JSON outputs with the input: `Kebakaran di Jakarta`
<br>For the Named Entity Recognition, you will get this JSON output:
```
{
"DESCRIPTION": [
"kebakaran"
],
"LOCATION": [
"jakarta"
],
"PERSON": [],
"WEAPON": []
}
```
</br>

<br>For the Classification Model, you will get this JSON output:
```
{
"label": "Fire"
}
```
</br>


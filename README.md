# adddev

Babashka script which adds a `devcontainer` configuration to a
clojure project in current folder.



## Usage 

Basic usage is via
``` bash

bb -m behrica.adddev.main
```

But as the script acts on local folder, it makes more sense to have it installed in some form in your computer,
using `bbin`for example.

```
bbin install io.github.behrica/add-devcontainer
```

Running the script creates a local folder `.devcontainer` with a `devcontainer.json`
including Clojure, babashka and lsp.

You can add the following parameters to add support for python or R into the devcontainer.
This updates as well `deps.edn`.

| parameter     | description                     |
|---------------|---------------------------------|
| :with-R       | adds R and clojisr              |
| :with-python  | adds python and clj-libpython  and a nrepl start script which runs nrepl in a configured venv |
| :with-noj     | add `noj` to deps.edn| 


### nrepl.sh
When the python support is activated, the script creates as well a `nrepl.sh` script,
which combines the setup of the venv via `poetry` and the nrepl start via `clj` in one commmand.
This makes sure, that the clojure process starts with the activated venv, which makes `libpython-clj` automatically
find the correct python environment at initialisation.

Using the appropiate `poetry` and `clj` commands this could as well be done more manual.


## From zero to clojure nrepl including usage of python and R from Clojure

### With Emacs

Assuming you have
- Docker
- [neil](https://github.com/babashka/neil)
- [devpod](https://devpod.sh/)
- add-devcontainer installed as `adddev` with [bbin](https://github.com/babashka/bbin)

installed and working on your machine,

you can get from "zero" to a clojure + python and R enabled devcontainer running nrepl (with working python bindings via [libpython-clj](https://github.com/clj-python/libpython-clj and R bindings with [clojisr](https://github.com/scicloj/clojisr)) ) on port 12345 via:



``` bash
neil new scratch foo
cd foo/
adddev :with-python :with-R
chmod +x nrepl.sh
devpod up .
devpod ssh . --command ./nrepl.sh
```
As devpod will forward port 12345 to your host, you can do `cider-connect` to `localhost:12345` with Emacs (or any other nrepl based IDE)

All changes of python packages listed in `pyproject.toml` will be picked-up and sychronized with the used venv 
when you stop/start `nrepl.sh`

The files of your localfolder a bind mount into the runnover docker.
So you can edit them "localy" with Emacs, and the running Docker will see
all changes immidiately. (You could as well edit them via Emacs/Tramp over the ssh connection, but this is not needed)

### With VSCode and Codespaces
Using those the `devpod` CLI usage is not needed and the devcontainer 
setup happens automatically by VSCode.
VSCode has a feature of "Reopen in folder in devcontainer" which does all automatically.
In this case the `nrepl.sh` would be started from
inside VSCode / Codespaces

If the code is on Github, Github allows to use Codespaces for an in-Browser VSCode IDE.

## Setup a complete Clojure data science project including R, python support 
It add as well [noj](https://github.com/scicloj/noj)


``` bash
neil new scratch datascience-scratch
cd datascience-scratch
adddev :with-python :with-R :with-noj
# Recommendet when using Emacs
chmod +x nrepl.sh
devpod up .
devpod ssh . --command ./nrepl.sh
```



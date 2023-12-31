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

Running the script creates a local folder `.devcontainer` with a `devcontainer.json`
including Clojure, babashka and lsp.

You can add the following parameters to add support for python or R into the devcontainer.
This updates as well `deps.edn`.

| parameter     | description                     |
|---------------|---------------------------------|
| :with-R       | adds R and clojisr              |
| :with-python  | adds python and clj-libpython  and a nrepl start script which runs nrepl in a configured venv |


### nrepl.sh
When the python support is activated, the script creates as well a `nrepl.sh` script,
which combines the setup of the venv via `poetry` and the nrepl start via `clj` in one commmand.
This makes sure, that the clojure process starts with the activated venv, which makes `libpython-clj` automatically
find the correct python environment at initialisation.

Using the appropiate `poetry` and `clj` commands this could as well be done more manual.


## From zero to clojure nrepl including usage of python

### With Emacs

Assuming you have
- [neil](https://github.com/babashka/neil)
- [devpod](https://devpod.sh/)
- add-devcontainer installed as `adddev` with [bbin](https://github.com/babashka/bbin)

you can get from "zero" to a clojure + python enabled devcontainer running nrepl (with working python bindings via [libpython-clj](https://github.com/clj-python/libpython-clj) ) on port 12345 via:



``` bash
neil new scratch foo
cd foo/
adddev :with-python
chmod +x nrepl.sh
devpod up .
devpod ssh .
./nrepl.sh
```
As devpod will forward port 12345 to your host, you can do `cider-connect` to `localhost:12345` with Emacs (or any other nrepl based IDE)

All changes of python packages listed in `pyproject.toml` will be picked-up and sychronized with the used venv 
when you stop/start `nrepl.sh`

### With VSCode and Codespaces
Using those the `devpod` CLI usage is not needed and the dvcontainer is automatically. In this case the `nrepl.sh` would be started from
inside VSCode / Codespaces




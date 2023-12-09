# adddev

Babashka script which adds a `devcontainer` configuration to a
clojure project in current folder.

## Usage 

``` bash
bb -m behrica.adddev.main
```

This creates a local folder `.devcontainer` with a devcontainer.json
including Clojure, babashka and lsp.

You can add the following parameters:

| :with-R       | adds R and clojisr              |
|---------------|---------------------------------|
| :with-python  | adds python and clj-libpython   |


## From zero to clojure nrepl including usage of python

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


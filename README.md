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


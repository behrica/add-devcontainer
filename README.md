# adddev

Babashka script which adds a `devcontainer` configuration to a
clojure project in current folder.

## Usage 

``` bash
bb -m behrica.adddev.main
```

This creates a local folder `.devcontainer` with a devcontainer.json
including Clojure, babashka and lsp.


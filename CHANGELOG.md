# unreleased

# 0.4.2
- setups a `enriched-clojure` executable, which is the same as `clojure` but is configured to have an
  [enriched](https://github.com/clojure-emacs/enrich-classpath) classpath and makes Cider show javadocs in autocomplete
- updated base image
- use "clojure -P -S threads 1"
- added :with-noj

# 0.3
- :with-python adds as well a bare-bone configuration for `poetry` 
- :with-python adds `nrepl.sh` which starts a nrepl and assures that it runs in a clean poetry managed venv
   
# 0.2

- added :with-R and :with-pytho


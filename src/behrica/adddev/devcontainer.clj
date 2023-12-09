(ns behrica.adddev.devcontainer
  (:require [babashka.fs :as fs]
            [clojure.pprint :as pp]
            [cheshire.core :as json]))


(defn make-dev-container-spec [name features]
  {"remoteUser" "vscode",
   "updateContentCommand" "clojure -P",
   "forwardPorts" ["12345" "7777"],
   "name" name,
   "build" {"dockerfile" "Dockerfile"},
   "remoteEnv" {"PATH" "${containerEnv:PATH}:/home/vscode/.asdf/shims"},
   "customizations" {"vscode" {"settings" {},
                               "extensions" ["vscjava.vscode-java-pack"
                                             "borkdude.clj-kondo"
                                             "betterthantomorrow.calva"]}},
   "features" features,
   "postCreateCommand" "sudo ln -fs /home/vscode/.asdf/shims/clojure /usr/local/bin/"})

(def clojure-features
  {
   "ghcr.io/devcontainers-contrib/features/clojure-asdf:2" {}
   "ghcr.io/devcontainers-contrib/features/bash-command:1" {"command" "apt-get update && apt-get install -y rlwrap"}})
   ;;
   ;; {}

(def base-deps
  {'org.clojure/clojure {:mvn/version "1.11.1"}})


(defn template-fn
  "Example template-fn handler.

  Result is used as the EDN for the template."
  [edn data]

  (let [features (merge clojure-features
                        (if (:with-python data) {"ghcr.io/devcontainers/features/python:1"          {}
                                                 "ghcr.io/devcontainers-contrib/features/poetry:2"  {} } )
                        (if (:with-R data)      {"ghcr.io/rocker-org/devcontainer-features/r-apt:0"  {}} ))
        deps (merge  base-deps
                     (if (:with-python data) {'clj-python/libpython-clj {:mvn/version "2.025"}} {})
                     (if (:with-R data) {'scicloj/clojisr {:mvn/version "1.0.0-BETA21"}} {}))]


    (assoc edn
           :features-str (json/generate-string features {:pretty true})
           :features features
           :deps-str (with-out-str (pp/pprint deps))
           :deps deps)))


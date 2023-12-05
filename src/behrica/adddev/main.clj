(ns behrica.adddev.main
  (:require
   [babashka.fs :as fs]
   [clojure.java.io :as io]
   [babashka.http-client :as http]
   ;; [behrica.adddev.devcontainer]
   [babashka.cli :as cli]))


(def cli-options {:port {:default 80 :coerce :long}
                  :help {:coerce :boolean}})

(defn download []
    (io/copy
     (:body (http/get "https://github.com/behrica/neil-devcontainer-ext/raw/main/devcontainer.zip"
                      {:as :stream}))
     (io/file "/tmp/devcontainer.zip")))

(defn -main [& _]

  ;; (prn :args *command-line-args*)
  ;; (prn (cli/parse-opts *command-line-args* {:spec cli-options}))

  (if (fs/exists? ".devcontainer")
    (println ".devcontainer exists. Skipp")
    (do
      (download)
      (fs/unzip "/tmp/devcontainer.zip" ".")
      (println ".devcontainer folder created"))))

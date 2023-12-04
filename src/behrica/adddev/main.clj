(ns behrica.adddev.main)

(require '[babashka.fs :as fs])
(require '[clojure.java.io :as io])
(require '[babashka.http-client :as http])
(require '[behrica.adddev.devcontainer])

;;
(require '[babashka.cli :as cli])

(def cli-options {:port {:default 80 :coerce :long}
                  :help {:coerce :boolean}})

(defn -main [& _]

  (prn :args *command-line-args*)
  (prn (cli/parse-opts *command-line-args* {:spec cli-options}))

  ;; (println "hello from github")
  ;; (println :neil-opts @babashka.neil/*neil-opts*)
  ;; (println :cwd (fs/cwd))

  ;; (io/copy
  ;;      (:body (http/get "https://github.com/behrica/neil-devcontainer-ext/raw/main/devcontainer.zip"
  ;;                       {:as :stream}))
  ;;      (io/file "/tmp/devcontainer.zip"))
  ;; (fs/unzip "/tmp/devcontainer.zip" ".")

  (println ".devcontainer folder created"))

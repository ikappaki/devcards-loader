(ns devcards-toc
  (:require [devcards.core :as dc]))

(defn ^:export init []
  (dc/start-devcard-ui!)
  (println :devcard-ui-started))


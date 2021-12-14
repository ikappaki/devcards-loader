(ns example.edn-cards
  (:require [devcards.core :as dc]))

(dc/defcard edn
  {:x 'io
   :y #{1 2 3}
   :ω 0.123})


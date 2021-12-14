(ns example.reagent-cards
  (:require
   [devcards.core :as dc]
   [reagent.core :as r]))

(dc/defcard-doc
  "# Markdown
  Once upon a ```time```.")

(defn counter+ []
  (let [c* (r/atom 0)]
    (fn []
      [:div
       [:button {:on-click #(swap! c* inc)} "inc"]
       [:div (str @c*) ]])))

(dc/defcard-rg counter
  [counter+])

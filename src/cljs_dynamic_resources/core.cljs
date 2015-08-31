(ns cljs-dynamic-resources.core
  (:require [cljs-dynamic-resources.utils :refer [append!]]
            [dommy.core :as dommy :refer-macros [sel sel1]]))
(def ^:private script-default-opts
  {:parent-sel :body
   :type "text/javascript"})

; <script src="src" type=":type" onload="cb"></script>
(defn add-script!
  "Append a script element"
  ([src cb opts]
    (let [opts (merge opts script-default-opts) ; normalize the options map
          parent-node (sel1 (:parent-sel opts))] ; get the container node
      (-> ; create a script element
          (dommy/create-element :script)
          ; set the type attribute
          (dommy/set-attr! :type (:type opts))
          ; set src attribute
          (dommy/set-attr! :src src)
          ; attach the on load event
          (dommy/listen! :load cb)
          ; append the newly created element to the parent node
          (append! parent-node))))
  ([src cb]
   (add-script! src cb nil))
  ([src]
   (add-script! src nil)))

(def ^:private style-default-opts
  {:parent-sel :head
   :rel "stylesheet"
   :type "text/css"})

; <style href="link-to-style" rel=":rel" onload="cb"></script>
(defn add-style!
  "Append a style element"
  ([src cb opts]
    (when (not (nil? src))
      (let [opts (merge opts style-default-opts)
            parent-node (sel1 (:parent-sel opts))]
        (-> ; create a script element
            (dommy/create-element :link)
            ; set the rel attribute
            (dommy/set-attr! :rel (:rel opts))
            ; set the type attribute
            (dommy/set-attr! :type (:type opts))
            ; set the href attribute
            (dommy/set-attr! :href src)
            ; attach the on load event
            (dommy/listen! :load cb)
            ; append the newly created element to the parent node
            (append! parent-node)))))
  ([src cb]
   (add-style! src cb style-default-opts))
  ([src]
   (add-style! src nil)))

; Example usage
; (add-scripts!
; [ {:src "/script1.js" :cb #(callback/script1) :opts opts}
;   {:src "/script2.js" :cb #(callback/script2)}
;   {:src "/script3.js"}]
;  #(global-callback))
(defn add-scripts!
  "Load a list of scripts taking care of the order"
  ([scripts global-cb]
    (let [{:keys [src cb opts] :as script} (first scripts)
          remain-scripts (subvec scripts 1)]
      (add-script! src
                   (fn [_]
                     (when (not (nil? cb)) (cb))
                     (if (> (count remain-scripts) 0)
                       (add-scripts! remain-scripts global-cb)
                       (when (not (nil? global-cb)) (global-cb))))
                   opts)))
  ([scripts]
   (add-scripts! scripts nil)))
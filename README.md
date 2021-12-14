# devcards-loader

A tiny library hack to load
[bhauman/devcards](https://github.com/bhauman/devcards) with only the
`marked` and `highlight.js` npm packages required as external
dependencies.

## Usage with shadow-cljs

Add `devcards-loader` in your `:deps` `deps.edn` file:

```clojure
{:deps {ikappaki/devcards-loader {:git/url "https://github.com/ikappaki/devcards-loader"
                                  :git/tag "v0.1.0"
                                  :git/sha "b6a4abda4994838ae3e4d556465e3445ec89e95b"}
        ;; ...
        }
 ;; ...
 }
```

This brings in `bhauman/devcards`; you can refer to it as normal from
your app.


Install the [markedjs/marked](https://github.com/markedjs/marked) v3.x
and [highlight.js](https://github.com/highlightjs/highlight.js)
(tested with v11.x) npm packages:

```shell
npm install marked@"<4.0.0"
```

```shell
npm install highlight.js
```

Optionally, add a new `:devcards` test build in your `shadow-cljs.edn`
file to load all namespaces ending in `-cards` as `devcards`:

```clojure
{:deps true          ;; delegate deps resolution to deps.edn file.

 ;; ...

 :builds
 {;; ...
  :devcards
  {:target           :browser-test
   :ns-regexp        "-cards$"            ;; load all namespaces ending in "-cards$".
   :test-dir         "out/devcards"
   :runner-ns        devcards-toc         ;; call custom fn devcards-toc/init.
   :compiler-options {:devcards           true
                      :output-feature-set :es8}
   :devtools
   {:http-port 3000
    :http-root "out/devcards"}}}}
```

The `devcards-toc` is just a regular `cljs` file in your `deps.edn`
`:paths`, with an exported `init` function that calls
`devcards.core/start-devcard-ui!` to start the devcards ui:

```clojure
(ns devcards-toc
  (:require [devcards.core :as dc]))

(defn ^:export init []
  (dc/start-devcard-ui!))
```

The server is listening on `http://localhost:3000`.

See [example](example) for a working setup.

## Credits

https://github.com/jacekschae/shadow-cljs-devcards
# Module for Extracting Software Engineering Artifacts from Text

This document describes the implementation of the semantic analysis module for WP3 Task 3.1. The module is implemented in JAVA and built on the [mate-tools pipeline](https://code.google.com/p/mate-tools/) for lemmatization, part-of-speech tagging, dependency parsing, and semantic role labelling.

## Authors

**This module is developed and maintained by** [Michael Roth](mroth@inf.ed.ac.uk)  

## Attribution

If you use this module in your work, please acknowledge it by citing one of the following publications:

Roth, M., Diamantopoulos, T., Klein, E., and Symeonidis, A. (in preparation). Software Requirements as an Application Domain for Natural Language Processing.
    
**Bibtex:**

    @article{Rothetal15,
	    Author = {Roth, Michael and Diamantopoulos, Themistoklis and Klein, Ewan and Symeonidis, Andreas},
	    Title = {{Software Requirements as an Application Domain for Natural Language Processing}},
	    Note = {in preparation}	    
    }
    
Roth, M., and Klein, E. (2015). Parsing Software Requirements with an Ontology-based Semantic Role Labeler. In Proceedings of the IWCS Workshop: Language and Ontologies 2015, London, UK, 14 April 2015.

**Bibtex:**

    @inproceedings{RothKlein15,
	    Author = {Roth, Michael and Klein, Ewan},
	    Title = {Parsing Software Requirements with an Ontology-based Semantic Role Labeler},
	    Year = {2015},
	    Booktitle = {Proceedings of the IWCS Workshop: Language and Ontologies 2015}
    }
    
## Main classes

This section describes the main classes for running and (re-)training the semantic analysis model used for extracting SE artifacts from text. 

### Running the pipeline

There are two ways in which the pipeline can be applied to process and semantically analyse software requirements expressed as natural language text: (1) as a standalone application from the command line, (2) via a Java class that provides static utility methods.

(1) If all project dependencies are installed, the full pipeline can be invoked using the following command:

`java -Xmx4g -cp <CLASSPATH> uk.ac.ed.inf.srl.CompletePipeline eng -tokenize -lemma models/lemma-train-eng.model -tagger models/tagger-train-eng.model -parser models/parse-train-eng.model -srl models/s-case.model [-printANN|-printRDF] -test <TEXTFILE> -out <OUTPUTFILE>`

Note that all `.model` files are statistical classification models that have been trained on corpora with syntactic and semantic annotations. The corpora are based on newswire text and a collection of requirements, respectively.   

(2) Alternatively, the following class and methods can be used to setup and query a static instance of the pipeline:

`JSONArray uk.ac.ed.inf.StaticPipeline.parseSentenceANN(String)`

`JSONObject uk.ac.ed.inf.StaticPipeline.parseSentenceTTL(String)`

Note that ANN and TTL represent to different output formats. ANN is a space-separated stand-off annotation format that specifies annotations as tuples in the form `<INSTANCE-ID, BEGIN-CHAR, END-CHAR>` and `<RELATION-ID, ARG1-ID, ARG2-ID>`. TTL is an inline annotation format based on the Resource Description Framework language and [Turtle](http://www.w3.org/TeamSubmission/turtle/).  

### Training the semantic analysis module

Given a pair of text file and stand-off annotations in the ANN format, the statistical model underlying the semantic analysis module can be re-trained by executing the following command:

`java -Xmx4g -cp <CLASSPATH> uk.ac.ed.inf.srl.TrainSRL -lemma models/lemma-train-eng.model -tagger models/tagger-train-eng.model -parser models/parse-train-eng.model <TEXTFILE> <ANNFILE> <MODELFILE>`

Note that the specified model file will be overwritten if it already exists.

## Package overview

Package  | Description
-------- | -----------
`uk.ac.ed.inf.srl`  | Contains all `main` function classes for running the parsing pipeline 
`uk.ac.ed.inf.srl.corpus` | Provides the classes for internally representing words, sentences, corpora and annotation objects
`uk.ac.ed.inf.srl.features` | Provides the classes that define features for statistical classification
`uk.ac.ed.inf.srl.io` | Provides IO classes for reading annotated texts and producing output in different formats
`uk.ac.ed.inf.srl.languages` | Contains classes related to language-specific settings
`uk.ac.ed.inf.srl.ml` | Provides classes and interfaces for learning and applying statistical models
`uk.ac.ed.inf.srl.options` | Defines command-line options and helper classes
`uk.ac.ed.inf.srl.pipeline` | Provides classes and interfaces for the individual semantic analysis steps
`uk.ac.ed.inf.srl.preprocessor` | Provides classes for syntactic preprocessing steps
`uk.ac.ed.inf.srl.rdf` | Utility classes for producing RDF representations
`uk.ac.ed.inf.srl.scorer` | Provides classes and interfaces for internally scoring predicted versus gold annotations  
`uk.ac.ed.inf.srl.util` | Miscellaneous utility classes 

## License

This program is free software; you can redistribute it and/or modify it under
the terms of the GNU General Public License as published by the Free Software
Foundation; either version 3 of the License, or any later version.

This program is distributed in the hope that it will be useful, but WITHOUT
ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
details.

You should have received a copy of the GNU General Public License along with
this program; if not, write to the Free Software Foundation, Inc., 51
Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.

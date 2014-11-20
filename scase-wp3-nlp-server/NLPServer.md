FORMAT: 1A

# NLP Server API
This is the API of the NLP Server created as part of the EU-funded project [S-CASE](http://www.scasefp7.eu/).
This page serves as usage instructions for the RESTful NLP web service.

## Authentication
The NLP Server uses BASIC/HTTP Authentication. Ask the administrator team for credentials on using the Server.
The credentials have to be included in the header of any request to the server. 

## Media Types
This API uses the JSON media-type for all possible actions. Requests and responses must all have JSON format.

## Error States
The common HTTP Response Status Codes are used.

# NLP Server API Root [/nlpserver]
This is the NLP Server API entry point. It contains a description of the service and links to the main resources.

## Retrieve the Entry Point [GET]

+ Response 200 (application/json)
    + Headers

            {
               "transfer-encoding": "chunked",
               "date": "...",
               "content-type": "application/json",
               "server": "..."
            }

    + Body

            {
                "module": "NLP Server",
                "description": "NLP Server of the EU-funded project S-CASE. See http://www.scasefp7.eu/",
                "_links": {
                    "send": "http://localhost:8010/nlpserver/send"
                }
            }


# Phrase [/nlpserver/phrase]
An endpoint for annotating English phrases.

Annotating requires sending a POST request including the phrase to be annotated and the annotation format and
receiving a full response containing the following attributes: 

- created_at
- phrase
- annotations
- annotation_format

## Annotating phrases [POST]

Using the NLP server for a phrase requires posting the following request:

+ Request (application/json)

        {
            "phrase": "...",
            "annotation_format": "ann" or "ttl"
        }

If the request is correct the server should return a Response 200

+ Response 200 (application/json)
   + Headers
   
            {
               "transfer-encoding": "chunked",
               "date": "...",
               "content-type": "application/json",
               "server": "..."
            }

   + Body

            {
               "phrase": "...",
               "created_at": "2014-11-06T13:44Z",
               "annotations": "..."
               "annotation_format": "ann" or "ttl"
            }

If the input format is not correct (e.g. the request is not in json), then the server returns a Response 400

+ Response 400 (text/html)
   + Headers

            {
               "content-length": "...",
               "content-language": "en",
               "server": "...",
               "connection": "close",
               "date": "...",
               "content-type": "text/html;charset=utf-8"
            }

   + Body

            (HTML formatted error)
            HTTP Status 400 - Bad Request
            The request sent by the client was syntactically incorrect.

Finally, if the format of the input is correct, yet the input itself is erroneous (e.g. a misspelled json key),
then the server returns a Response 400

+ Response 422 (text/plain)
   + Headers

            {
               "transfer-encoding": "chunked",
               "date": "...",
               "content-type": "text/plain",
               "server": "..."
            }

   + Body

            JSONObject["..."] not found.


# Sentence [/nlpserver/sentence]
An endpoint for annotating English sentences. A sentence corresponds to an individual requirement.

Annotating requires sending a POST request including the sentence to be annotated and the annotation format and
receiving a full response containing the following attributes: 

- created_at
- sentence
- annotations
- annotation_format

## Annotating sentences [POST]

Using the NLP server for a sentence requires posting the following request:

+ Request (application/json)

        {
            "sentence": "...",
            "annotation_format": "ann" or "ttl"
        }

If the request is correct the server should return a Response 200

+ Response 200 (application/json)
   + Headers
   
            {
               "transfer-encoding": "chunked",
               "date": "...",
               "content-type": "application/json",
               "server": "..."
            }

   + Body

            {
               "sentence": "...",
               "created_at": "2014-11-06T13:44Z",
               "annotations": "..."
               "annotation_format": "ann" or "ttl"
            }

If the input format is not correct (e.g. the request is not in json), then the server returns a Response 400

+ Response 400 (text/html)
   + Headers

            {
               "content-length": "...",
               "content-language": "en",
               "server": "...",
               "connection": "close",
               "date": "...",
               "content-type": "text/html;charset=utf-8"
            }

   + Body

            (HTML formatted error)
            HTTP Status 400 - Bad Request
            The request sent by the client was syntactically incorrect.

Finally, if the format of the input is correct, yet the input itself is erroneous (e.g. a misspelled json key),
then the server returns a Response 400

+ Response 422 (text/plain)
   + Headers

            {
               "transfer-encoding": "chunked",
               "date": "...",
               "content-type": "text/plain",
               "server": "..."
            }

   + Body

            JSONObject["..."] not found.


# Project [/nlpserver/project]
An endpoint for annotating a software project. Each sentence is an individual requirement of the project.

Annotating requires sending a POST request including the requirements of the project to be annotated and
the annotation format and receiving a full response containing the following attributes: 

- created_at
- project_requirements
- annotations
- annotation_format

## Annotating projects [POST]

Using the NLP server for a project requires posting the following request:

+ Request (application/json)

        {
            "project_name": "...",
            "project_requirements": [
                                       {"id": "FR1", "text": "..."},
                                       {"id": "FR2", "text": "..."},
                                       {"id": "FR3", "text": "..."},
                                       ...
                                    ],
            "annotation_format": "ann" or "ttl"
        }

If the request is correct the server should return a Response 200

+ Response 200 (application/json)
   + Headers
   
            {
               "transfer-encoding": "chunked",
               "date": "...",
               "content-type": "application/json",
               "server": "..."
            }

   + Body

            {
               "project_name": "...",
               "project_requirements": [
                                          {"id": "FR1", "text": "..."},
                                          {"id": "FR2", "text": "..."},
                                          {"id": "FR3", "text": "..."},
                                          ...
                                       ],
               "created_at": "2014-11-06T13:44Z",
               "annotations": "..." (single ann) or [{"id": "FR1", "annotation": "..."}, {"id": "FR2", "annotation": "..."}, ...], (list of ttls)
               "annotation_format": "ann" or "ttl"
            }

If the input format is not correct (e.g. the request is not in json), then the server returns a Response 400

+ Response 400 (text/html)
   + Headers

            {
               "content-length": "...",
               "content-language": "en",
               "server": "...",
               "connection": "close",
               "date": "...",
               "content-type": "text/html;charset=utf-8"
            }

   + Body

            (HTML formatted error)
            HTTP Status 400 - Bad Request
            The request sent by the client was syntactically incorrect.

Finally, if the format of the input is correct, yet the input itself is erroneous (e.g. a misspelled json key),
then the server returns a Response 400

+ Response 422 (text/plain)
   + Headers

            {
               "transfer-encoding": "chunked",
               "date": "...",
               "content-type": "text/plain",
               "server": "..."
            }

   + Body

            JSONObject["..."] not found.

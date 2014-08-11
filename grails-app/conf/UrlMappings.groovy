class UrlMappings {

	static mappings = {

        "/campaigns/$id"(controller: "campaigns", parseRequest: true){
            action = [GET: 'show']
        }

        "/campaigns"(controller: "campaigns", parseRequest: true){
            action = [GET: 'index']
        }

        "/campaigns/$id/turnon"(controller: "campaigns", parseRequest: true){
            action = [PUT: 'turnOn']
        }

        "/campaigns/$id/turnoff"(controller: "campaigns", parseRequest: true){
            action = [PUT: 'turnOff']
        }

        "/campaigns"(controller: "campaigns", parseRequest: true){
            action = [POST: 'create']
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}

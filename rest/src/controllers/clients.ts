import { Context } from 'https://deno.land/x/oak/mod.ts';
import { Bson } from "https://deno.land/x/mongo@v0.29.4/mod.ts";

import ClientSchema from '../models/Client.ts';
import db from '../services/database.ts';

const clients = db.collection<ClientSchema>("clients");

// Client Controller
const clientController = {
	// Obtener un cliente by query
	getClient: async ({ params, response }: { params: { id: string }, response: any }) => {
		try {
			const client = await clients.findOne({ _id: new Bson.ObjectId(params.id) }, { noCursorTimeout: false } as any);
			response.status = 200;
			response.body = {
				sucess: true,
				client
			}
		}
		catch(err) {
			console.log(err);
			response.status = 500;
			response.body = {
				sucess: false,
				msg: "Something is wrong :("
			}
		}
	},
	// Obtener clientes
	getClientList: async (ctx: Context) => {
		// Obteniendo body json
		const body = ctx.request.body({ type: 'json' }); 
		console.log(body);
 		const value = await body.value;
 		const clientIds: string[] = value.clients;

		try {
			const clientList: any[] = [];
			for(let i = 0; i < clientIds.length; i++) {
				const client = await clients.findOne({ _id: new Bson.ObjectId(clientIds[i]) }, { noCursorTimeout: false } as any);
				clientList[i] = client;
			}

			ctx.response.status = 200;
			ctx.response.body = {
				sucess: true,
				clients: clientList,
			}
		}
		catch(err) {
			console.log(err);
			ctx.response.status = 500;
			ctx.response.body = {
				sucess: false,
				msg: "Something is wrong :("
			}
		}
	}
}

export default clientController;
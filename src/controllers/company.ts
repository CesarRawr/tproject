import { Context } from 'https://deno.land/x/oak/mod.ts';
import { Bson } from "https://deno.land/x/mongo@v0.29.4/mod.ts";

import CompanySchema from '../models/Company.ts';
import db from '../services/database.ts';

const companies = db.collection<CompanySchema>("companies");

// Client Controller
const companyController = {
	// Obtener un compañia by query
	getCompany: async ({ params, response }: { params: { id: string }, response: any }) => {
		try {
			const company = await companies.findOne({ _id: new Bson.ObjectId(params.id) }, { noCursorTimeout: false } as any);				

			response.status = 200;
			response.body = {
				sucess: true,
				company
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
	getCompanyList: async (ctx: Context) => {
		// Obteniendo body json
		const body = ctx.request.body({ type: 'json' }); 
 		const value = await body.value;

 		const companyIds: string[] = value.companies;
		try {
			const companyList: any[] = [];

			// No pude hacer un map asincrono :c
			for(let i = 0; i < companyIds.length; i++) {
				const company = await companies.findOne({ _id: new Bson.ObjectId(companyIds[i]) }, { noCursorTimeout: false } as any);
				companyList[i] = company;
			}				

			ctx.response.status = 200;
			ctx.response.body = {
				sucess: true,
				companies: companyList,
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

export default companyController;
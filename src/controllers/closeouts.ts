import { Context } from 'https://deno.land/x/oak/mod.ts';
import { Bson } from "https://deno.land/x/mongo@v0.29.4/mod.ts";

import CloseoutSchema from '../models/Closeout.ts';
import db from '../services/database.ts';

const closeouts = db.collection<CloseoutSchema>("closeouts");

// Client Controller
const closeoutController = {
	addCloseout: async (ctx: Context) => {
		const body = ctx.request.body({ type: "json" });
		const data = await body.value;
		
		try {
			const insert = await closeouts.insertOne({
				cliente_id: data.cliente_id,
				vendedor_id: data.vendedor_id,
				precio_tortilla: data.precio_tortilla,
				precio_totopos: data.precio_totopos,
				venta: data.venta,
				merma: data.merma,
				fecha: data.fecha,
				total: data.total
			}, { noCursorTimeout: false } as any);

			ctx.response.status = 200;
			ctx.response.body = {
				sucess: true,
				msg: "Sucess operation",
				something: insert
			}
		}
		catch (err) {
			console.log(err);
			ctx.response.status = 500;
			ctx.response.body = {
				sucess: false,
				msg:  "Something is wrong :("
			}
		}
	}
}

export default closeoutController;

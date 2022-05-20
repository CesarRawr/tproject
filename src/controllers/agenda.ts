import { Context } from 'https://deno.land/x/oak/mod.ts';
import { Bson } from "https://deno.land/x/mongo@v0.29.4/mod.ts";

import ScheduleSchema from '../models/Schedule.ts';
import db from '../services/database.ts';

const agenda = db.collection<ScheduleSchema>("agenda");

// Agenda Controller
const agendaController = {
	// Obtener scheules de un usuario
	getAgenda: async (ctx: Context) => {
		try {
			const scheduleList: any[] = await agenda.find({}, { noCursorTimeout: false } as any).toArray();

			ctx.response.status = 200;
			ctx.response.body = {
				sucess: true,
				agenda: scheduleList
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
	},
	// Agregar un schedule
	addSchedule: async (ctx: Context) => {
		const body = ctx.request.body({ type: "json" });
		const data = await body.value;
		
		try {
			const insert = await agenda.insertOne({
					cliente: data.cliente,
					tortilla: data.tortilla,
					totopos: data.totopos,
					fecha_entrega: data.fecha_entrega,
					vendedor: data.vendedor,
				}, 
				{ 
					noCursorTimeout: false 
				} as any
			);

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
	},
	getSchedule: async ({ params, response }: { params: { id: string }, response: any }) => {
		try {
			const schedule = await agenda.findOne({ _id: new Bson.ObjectId(params.id) }, { noCursorTimeout: false } as any);				

			response.status = 200;
			response.body = {
				sucess: true,
				schedule,
			}
		}
		catch(err) {
			console.log(err);
			response.status = 500;
			response.body = {
				sucess: false,
				msg: "Something is wrong :(",
			}
		}
	}
}

export default agendaController;
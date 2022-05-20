import { Context } from 'https://deno.land/x/oak/mod.ts';
import type { Payload } from 'https://deno.land/x/djwt@v2.4/mod.ts';

import UserSchema from '../models/User.ts';
 
import jwtService from '../services/jwt.ts';
import db from '../services/database.ts';

const users = db.collection<UserSchema>("users");
const userController = {
	// Verificar datos
 	loginHandler: async (ctx: Context) => {
 		const body = ctx.request.body({ type: 'json' }); 
 		const value = await body.value;

 		try {
 			const user = await users.findOne({ email: value.email }, { noCursorTimeout: false } as any);
 			if(!user) {
 				ctx.response.status = 401;
 				ctx.response.body = {
 					sucess: false,
 					msg: "Usuario o contraseña incorrectos"
 				}
 				return;
 			}

 			if(value.pass !== user.pass) {
 			 	ctx.response.status = 401;
	 			ctx.response.body = {
	 				sucess: false,
	 				msg: "Usuario o contraseña incorrectos"
	 			}
	 			return;
 			}

 			const token = await jwtService.generateToken(user);
 			ctx.response.status = 200;
 			ctx.response.body = {
 				sucess: true,
 				token,
 			}
  		}
 		catch(err) {
 			console.log(err);
 			ctx.response.status = 500;
 			ctx.response.body = {
 				msg: err
 			};
 		}
	}
}

export default userController;
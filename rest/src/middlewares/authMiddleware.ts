import { Context } from 'https://deno.land/x/oak/mod.ts';
import jwtService from '../services/jwt.ts';

const tokenVerify = async (ctx: Context, next: Function) => {
	const auth: string | null = ctx.request.headers.get("Authorization");
	try {
		let token: string = !auth ? "no-token": auth.split(' ')[1];

		if(await jwtService.validateToken(token)) {
			await next();
			return;
		}
	}
	catch (err: any) {
		console.log(err);
		ctx.response.status = 401;
		ctx.response.body = {
			msg: "Invalid token",
		}
	}
}

export default tokenVerify;

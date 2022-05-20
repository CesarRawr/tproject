import { Application, Context } from 'https://deno.land/x/oak/mod.ts';
import { parse } from 'https://deno.land/std/flags/mod.ts';

import users from './src/routes/users.ts';

const { args } = Deno;
const DEFAULT_PORT = 8080;
const app = new Application();
const argPort = parse(args).port;

// Loggin
app.use(async (ctx: Context, next: Function) => {
	console.log(`HTTP ${ ctx.request.method } on ${ ctx.request.url }`);
	await next();
});

// Setting user routes
app.use(users.allowedMethods());
app.use(users.routes());

await app.listen({ port: argPort ? Number(argPort) : DEFAULT_PORT });

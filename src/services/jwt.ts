import { create, verify, getNumericDate } from 'https://deno.land/x/djwt@v2.2/mod.ts';
import type { Payload, Header } from 'https://deno.land/x/djwt@v2.2/mod.ts';

const key: string = "idWJ+JObB2ZFo2H9nTrO2jAljYAwVGvOCngjSbZo3hg=";

const header: Header = {
	alg: "HS256",
	typ: "JWT"
}

const jwtService = {
	generateToken: async (user: any): Promise<string> => {
		const payload: Payload = {
			id: user._id,
			nickname: user.nickname,
			type: user.type,
			clients: user.clients,
			companies: user.companies,
			exp: getNumericDate(60 * 60)
		}
		
		return await create(header, payload, key);
	},
	validateToken: async (token: string): Promise<Payload> => {
		return await verify(token, key, "HS256");
	}
}

export default jwtService;

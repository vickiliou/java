package br.com.fiap.validator;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.fiap.util.BundleUtil;

@FacesValidator("dataFutura")
public class ValidarDataFutura implements Validator {

	@Override
	public void validate(FacesContext context, 
				UIComponent component, Object value) throws ValidatorException {
		
		//Pega a data de hj
		Date hoje = new Date();
		
		if (hoje.before((Date)value)) {
			String mensagem = BundleUtil.getMessageResourceString(
				context.getApplication().getMessageBundle(),
				"msg_error",null,
				context.getViewRoot().getLocale()); 
					
			throw new ValidatorException(
					new FacesMessage(mensagem));
		}
		
	}

}
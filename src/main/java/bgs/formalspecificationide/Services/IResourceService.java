package bgs.formalspecificationide.Services;

import bgs.formalspecificationide.Exceptions.KeyNotFoundException;

public interface IResourceService {

    String getText(String name) throws KeyNotFoundException;

}

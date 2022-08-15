package bgs.formalspecificationide.services;

import bgs.formalspecificationide.exceptions.KeyNotFoundException;

public interface IResourceService {

    String getText(String name) throws KeyNotFoundException;

}

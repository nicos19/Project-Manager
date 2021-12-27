package project_management;

/**
 * Created by Nico Sonner on 27.12.2021.
 *
 * A Method instance is used to describe a method  and how it shall look like.
 * Methods instances are parts of a class plan.
 */
class Method {
    private String name;
    private boolean isOpen = false;
    private Description description;
    private ClassPlan parentClassPlan;


    Method(String methodName, ClassPlan parentPlan) {
        name = methodName;
        description = new Description(name);
        parentClassPlan = parentPlan;

        // initialize methods's description
        getDescription().setClassMethodLook();
    }

    /**
     * Gets the name of this method.
     * @return the task name
     */
    String getName() {
        return name;
    }

    /**
     * Gets the description of this method.
     * @return the description
     */
    Description getDescription() {
        return description;
    }
}
